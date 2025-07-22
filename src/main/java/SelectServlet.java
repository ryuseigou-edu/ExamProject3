import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

import creature.Character;
import creature.Monster;

import creature.character.Hero;
import creature.character.SuperHero;
import creature.character.Thief;
import creature.character.Wizard;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/SelectServlet")//クラス名と同じ
public class SelectServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        HttpSession session = request.getSession();
        ArrayList<Character> party = (ArrayList<Character>) session.getAttribute("party");
        ArrayList<Monster> monsters = (ArrayList<Monster>) session.getAttribute("monsters");
        Iterator<Character> itChar = (Iterator<Character>) session.getAttribute("itChar");
        Iterator<Monster> itMon = (Iterator<Monster>) session.getAttribute("itMon");

        Character curChar = itChar.next();
        ArrayList<String> actions = getAction(curChar);

        session.setAttribute("party", party);
        session.setAttribute("monsters", monsters);
        session.setAttribute("itChar", itChar);
        session.setAttribute("itMon", itMon);
        session.setAttribute("curChar", curChar);

        //HTML
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>行動を選択するドン！</h1>");
        out.println("<h2>---味方パーティー---</h2>");
        for(Character character : party) {
            out.println(character.showStatus());
            out.println("<br>");
        };
        out.println("<br>");

        out.println("<h2>---敵グループ---</h2>");
        for(Monster monster : monsters) {
            out.println(monster.showStatus());
            out.println("<br>");
        }
        out.println("<br>");
        out.println("<<<["+curChar.getName()+"]のターン>>>");
        out.println("<form action=\"BattleServlet\" method=\"post\">");
        out.println("<select name=\"action\">");
        int cnt = 0;
        for(String actionName : actions) {
            out.println("<option value=\"" + (cnt++) + "\">" +actionName+ "</option>");
        }
        out.println("</select>");
        out.println("---->");
        out.println("<select name=\"target\">");
        cnt = 0;
        for(Monster monster : monsters) {
            out.println("<option value=\"" + (cnt++) + "\">" + monster.getName()+monster.getSuffix() + "</option>");
        }
        out.println("</select>");
        out.println("<button type=\"submit\">決定</button>");//ボタンの設置
        out.println("</form>");//formの内容がここまでと宣言する
        out.println("</body></html>");
        //HTML終わり
    }

    private static ArrayList<String> getAction(Character curChar) {
        ArrayList<String> actions = new ArrayList<>();
        switch (curChar) {
            case SuperHero curSuperHero -> {
                actions.add("攻撃");
            }
            case Hero curHero -> {
                actions.add("攻撃");
                actions.add("スーパーヒーローに進化！");
            }
            case Thief curThief -> {
                actions.add("攻撃");
                actions.add("守り");
            }
            case Wizard curWizard -> {
                actions.add("攻撃");
                actions.add("魔法攻撃");
            }
            default -> {
            }
        }
        return actions;
    }
}
