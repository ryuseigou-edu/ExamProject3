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
        Iterator<Monster> itMon = (Iterator<Monster>) session.getAttribute("itMon");



        //HTML
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>戦闘結果</h1>");
        out.println("<p>" +attackMessage+ "</p>");
        out.println("<hr>");
        out.println("<h2>---敵の攻撃---</h2>");
        while (itMon.hasNext()) {
            Monster curMon = itMon.next();
            Character curTar = party.get((int) (Math.random() * party.size()));
            if (curMon instanceof Matango curMatango) {
                curMatango.attack(curTar);
            } else if (curMon instanceof Goblin curGoblin) {
                curGoblin.attack(curTar);
            } else if (curMon instanceof Slime curSlime) {
                curSlime.attack(curTar);
            }
            if (!curTar.isAlive()) {
                curTar.die();
                party.remove(curTar);
            }
            if (monsters.isEmpty()) {
                break;
            }
        }

        out.println("<br>");
        out.println("<form action=\"SelectServlet\" method=\"post\">");
        out.println("<button type=\"submit\">次のキャラクターへ</button>");//ボタンの設置
        out.println("</form>");//formの内容がここまでと宣言する
        out.println("</body></html>");
        //HTMLおわり
    }
}