import java.io.*;
import java.util.ArrayList;

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

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private static final int MONSTERS_SIZE = 5;
    private static int matangoCnt = 0;
    private static int goblinCnt = 0;
    private static int slimeCnt = 0;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");

        HttpSession session = request.getSession();

        ArrayList<Character> party = new ArrayList<>();
        Hero hero = new Hero("勇者", 100);
        Wizard wizard = new Wizard("魔法使い", 60, 10);
        Thief thief = new Thief("盗賊", 70);
        party.add(hero);
        party.add(wizard);
        party.add(thief);

        for(int i = 0; i < MONSTERS_SIZE; i++) {

        }

        session.setAttribute("party", party);

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<form action=\"SelectServlet\">");//SelectServletに送るためのformを作る
        out.println("誰に攻撃しますか？");
        out.println("<input type=\"text\" name=\"targetIndex\">");
        out.println("<button type=\"submit\">スタート</button>");//ボタンの設置
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