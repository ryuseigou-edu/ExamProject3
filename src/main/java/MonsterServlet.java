import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

import creature.Character;
import creature.Monster;
import creature.character.Hero;
import creature.character.SuperHero;
import creature.character.Thief;
import creature.character.Wizard;
import creature.monster.Goblin;
import creature.monster.Matango;
import creature.monster.Slime;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/MonsterServlet")

public class MonsterServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");

        HttpSession session = request.getSession();

        ArrayList<Character> party = (ArrayList<Character>) session.getAttribute("party");
        ArrayList<Monster> monsters = (ArrayList<Monster>) session.getAttribute("monsters");
        Iterator<Character> itChar = (Iterator<Character>) session.getAttribute("itChar");
        Iterator<Monster> itMon = monsters.iterator();

        ArrayList<String> actions = new ArrayList<>();
        ArrayList<String> messages = new ArrayList<>();

        while (itMon.hasNext()) {
            Monster curMon = itMon.next();
            Character curTar = party.get((int) (Math.random() * party.size()));
            if (curMon instanceof Matango curMatango) {
                actions.add(curMatango.attack(curTar));
            } else if (curMon instanceof Goblin curGoblin) {
                actions.add(curGoblin.attack(curTar));
            } else if (curMon instanceof Slime curSlime) {
                actions.add(curSlime.attack(curTar));
            }
            if (!curTar.isAlive()) {
                messages.add(curTar.die());
                party.remove(curTar);
            }
            if (monsters.isEmpty()) {
                break;
            }
        }

        session.setAttribute("party", party);
        session.setAttribute("monsters", monsters);
        session.setAttribute("itChar", party.iterator());


        //HTML
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>戦闘結果</h1>");
        //out.println("<p>" +attackMessage+ "</p>");
        for(String action : actions) {
            out.println("<p>"+action+"</p>");
        }
        for(String message : messages) {
            out.println("<p>"+message+"</p>");
        }
        out.println("<br>");
        out.println("<hr>");
        out.println("<h2>---味方パーティー---</h2>");
        for(Character character : party) {
            out.println(character.showStatus());
            out.println("<br>");
        }
        out.println("<hr>");
        out.println("<br>");
        if(party.isEmpty() || monsters.isEmpty()) {
            out.println("<form action=\"BattleEndServlet\" method=\"post\">");
            out.println("<button type=\"submit\">リザルトへ</button>");
        } else {
            out.println("<form action=\"SelectServlet\" method=\"post\">");
            out.println("<button type=\"submit\">味方のターンヘ</button>");
        }
        out.println("</form>");//formの内容がここまでと宣言する
        out.println("</body></html>");
        //HTMLおわり
    }
}