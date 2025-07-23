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

@WebServlet("/BattleServlet")

public class BattleServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");

        HttpSession session = request.getSession();

        ArrayList<Character> party = (ArrayList<Character>) session.getAttribute("party");
        ArrayList<Monster> monsters = (ArrayList<Monster>) session.getAttribute("monsters");
        Iterator<Character> itChar = (Iterator<Character>) session.getAttribute("itChar");

        int action = Integer.parseInt(request.getParameter("action"));
        int target = Integer.parseInt(request.getParameter("target"));
        Character curChar = (Character) session.getAttribute("curChar");
        Monster curTar = monsters.get(target);

        System.out.println("Action: " + action);
        System.out.println("Target: " + target);
        System.out.println("Current Character: " + curChar.getName());

        String attackMessage = "";
        switch (curChar) {
            case SuperHero curSuperHero -> {
                attackMessage = curSuperHero.attack(curTar);
            }
            case Hero curHero -> {
                switch (action) {
                    case 0:
                        attackMessage = curHero.attack(curTar);
                        break;
                    case 1:
                        SuperHero curSuperHero = new SuperHero(curHero);
                        party.set(party.indexOf(curHero), curSuperHero);
                        if (!curSuperHero.isAlive()) {
                            System.out.print("が、");
                            curSuperHero.die();
                            System.out.print("www");
                            itChar.remove();
                        }
                        break;
                }
            }
            case Thief curThief -> {
                switch (action) {
                    case 0:
                        attackMessage = curThief.attack(curTar);
                        break;
                    case 1:
                        curThief.guard();
                        break;
                }
            }
            case Wizard curWizard -> {
                switch (action) {
                    case 0:
                        attackMessage = curWizard.attack(curTar);
                        break;
                    case 1:
                        attackMessage = curWizard.magic(curTar);
                        break;
                }
            }
            default -> {
            }
        }



        //HTML
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>戦闘結果</h1>");
        out.println("<p>" +attackMessage+ "</p>");
        out.println("<hr>");
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
        if (itChar.hasNext()) {
            out.println("<form action=\"SelectServlet\" method=\"post\">");
        } else {
            out.println("<form action=\"MonsterServlet\" method=\"post\">");
        }
        if(itChar.hasNext()) {
            out.println("<button type=\"submit\">次のキャラクターへ</button>");//ボタンの設置
        } else {
            out.println("<button type=\"submit\">敵のターンへ</button>");
        }
        out.println("</form>");//formの内容がここまでと宣言する
        out.println("</body></html>");
        //HTMLおわり
    }
}