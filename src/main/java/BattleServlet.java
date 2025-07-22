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

@WebServlet(name = "BattleServlet")
public class BattleServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");

        HttpSession session = request.getSession();

        ArrayList<Character> party = (ArrayList<Character>) session.getAttribute("party");
        ArrayList<Monster> monsters = (ArrayList<Monster>) session.getAttribute("monsters");
        Iterator<Character> itChar = (Iterator<Character>) session.getAttribute("itChar");
        Iterator<Monster> itMon = (Iterator<Monster>) session.getAttribute("itMon");

        int action = Integer.parseInt(request.getParameter("action"));
        int target = Integer.parseInt(request.getParameter("target"));
        Character curChar = (Character) session.getAttribute("curChar");
        Monster curTar = monsters.get(target);

        switch (curChar) {
            case SuperHero curSuperHero -> {
                curSuperHero.attack(curTar);
            }
            case Hero curHero -> {
                switch (action) {
                    case 0:
                        curHero.attack(curTar);
                        break;
                    case 1:
                        SuperHero curSuperHero = new SuperHero(curHero);
                        party.set(party.indexOf(curHero), curSuperHero);                        if (!curSuperHero.isAlive()) {
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
                        curThief.attack(curTar);
                        break;
                    case 1:
                        curThief.guard();
                        break;
                }
            }
            case Wizard curWizard -> {
                switch (action) {
                    case 0:
                        curWizard.attack(curTar);
                        break;
                    case 1:
                        curWizard.magic(curTar);
                        break;
                }
            }
            default -> {
            }
        }



        //html
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>戦闘結果</h1>");
        out.println("");
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
        out.println("<form action=\"SelectServlet\" method=\"post\">");//SelectServletに送るためのformを作る
        out.println("<button type=\"submit\">戦闘開始！</button>");//ボタンの設置
        out.println("</form>");//formの内容がここまでと宣言する
        out.println("</body></html>");
    }

    private static Monster choiceEnemy() {
        switch ((int)(Math.random() * 3)) {
            case 0:
                return new Matango(45, (char) ('A' + matangoCnt++));
            case 1:
                return new Goblin(50, (char) ('A' + goblinCnt++));
            case 2:
                return new Slime(40, (char) ('A' + slimeCnt++));
            default:
                throw new IllegalArgumentException();
        }
    }
}