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

@WebServlet("/BattleEndServlet")
public class BattleEndServlet extends HttpServlet {
    private static final int MONSTERS_SIZE = 5;
    private static int matangoCnt = 0;
    private static int goblinCnt = 0;
    private static int slimeCnt = 0;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");

        HttpSession session = request.getSession();

        ArrayList<Character> party = (ArrayList<Character>) session.getAttribute("party");
        ArrayList<Monster> monsters = (ArrayList<Monster>) session.getAttribute("monsters");
        PrintWriter out = response.getWriter();

        //HTML
        out.println("<html><body>");
        out.println("<h1>戦闘終了！</h1>");
        out.println("<h2>---味方パーティー---</h2>");
        if(party.isEmpty()) {
            out.println("<p>味方パーティは全滅してしまった…</p>");
        }
        for(Character character : party) {
            out.println(character.showStatus());
            out.println("<br>");
        }
        out.println("<br>");

        out.println("<h2>---敵グループ---</h2>");
        if(monsters.isEmpty()) {
            out.println("<p>敵を全て倒した!</p>");
        }
        for(Monster monster : monsters) {
            out.println(monster.showStatus());
            out.println("<br>");
        }
        out.println("<br>");
        out.println("<hr>");
        if(monsters.isEmpty()) {
            out.println("<h2>"+ party.getFirst().getName()+ "達は勝利した！</h2>");
        }
        //HTML終わり
    }
}