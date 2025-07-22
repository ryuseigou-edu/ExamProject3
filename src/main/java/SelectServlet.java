import java.io.*;
import java.util.ArrayList;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/SelectServlet")//クラス名と同じ
public class SelectServlet extends HttpServlet {//HTTP通信を処理するクラスを継承
    //doGetメソッドを追加し、メソッドの呼び出し元であるHttpServletに例外の伝播をする
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        HttpSession session = request.getSession();
        ArrayList<String> names = (ArrayList<String>) session.getAttribute("names");
        int targetIndex = Integer.parseInt(request.getParameter("targetIndex"));
        session.setAttribute("names", names);

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        for (String name : names) {
            out.println(name + "<br>");
        }

        out.println("の中から");
        out.println(names.get(targetIndex) + "を選択しました");
        out.println("</body></html>");
    }
}
