import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

import creature.Character;
import creature.Monster;
import creature.character.Hero;
import creature.character.Thief;
import creature.character.Wizard;
import creature.monster.Goblin;
import creature.monster.Matango;
import creature.monster.Slime;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "HelloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private static final int MONSTERS_SIZE = 5;
    private static int matangoCnt = 0;
    private static int goblinCnt = 0;
    private static int slimeCnt = 0;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");

        HttpSession session = request.getSession();

        ArrayList<Character> party = new ArrayList<>();
        ArrayList<Monster> monsters = new ArrayList<>();

        Hero hero = new Hero("勇者", 100);
        Wizard wizard = new Wizard("魔法使い", 60, 20);
        Thief thief = new Thief("盗賊", 70);
        party.add(hero);
        party.add(wizard);
        party.add(thief);

        for(int i = 0; i < MONSTERS_SIZE; i++) {
            monsters.add(choiceEnemy());
        }

        session.setAttribute("party", party);
        session.setAttribute("monsters", monsters);
        session.setAttribute("itChar", party.iterator());

        PrintWriter out = response.getWriter();

        //HTML
        out.println("<html><body>");
        out.println("<h1>戦闘準備！</h1>");
        out.println("<h2>---味方パーティー---</h2>");
        for(Character character : party) {
            out.println(character.showStatus());
            out.println("<br>");
        }
        out.println("<br>");

        out.println("<h2>---敵グループ---</h2>");
        for(Monster monster : monsters) {
            out.println(monster.showStatus());
            out.println("<br>");
        }
        out.println("<br>");
        out.println("<form action=\"SelectServlet\" method=\"post\">");
        out.println("<button type=\"submit\">戦闘開始！</button>");//ボタンの設置
        out.println("</form>");//formの内容がここまでと宣言する
        out.println("</body></html>");
        //HTML終わり
    }

    private static Monster choiceEnemy() {
        return switch ((int) (Math.random() * 3)) {
            case 0 -> new Matango(45, (char) ('A' + matangoCnt++));
            case 1 -> new Goblin(50, (char) ('A' + goblinCnt++));
            case 2 -> new Slime(40, (char) ('A' + slimeCnt++));
            default -> throw new IllegalArgumentException();
        };
    }
}