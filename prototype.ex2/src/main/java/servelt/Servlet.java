package servelt;



import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import prototype.ex2.*;
import java.util.ArrayList;
import java.util.List;

public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Shape> shapes = new ArrayList<>();

        // Retrieve parameters from the request
        String shapeType = request.getParameter("shapeType");
        int x = Integer.parseInt(request.getParameter("x"));
        int y = Integer.parseInt(request.getParameter("y"));
        String color = request.getParameter("color");

        // Create shape based on user input
        Shape userShape = createShape(shapeType, x, y, color);
        shapes.add(userShape);

        // Clone and compare shapes
        List<Shape> shapesCopy = cloneAndCompare(shapes);

        // Set attributes in request scope for JSP rendering
        request.setAttribute("shapesCopy", shapesCopy);

        // Forward the request to the JSP page
        request.getRequestDispatcher("/prototype-ex2.jsp").forward(request, response);
    }

    private Shape createShape(String shapeType, int x, int y, String color) {
        if ("circle".equalsIgnoreCase(shapeType)) {
            Circle circle = new Circle();
            circle.x = x;
            circle.y = y;
            circle.radius = 15; // Default radius for simplicity
            circle.color = color;
            return circle;
        } else if ("rectangle".equalsIgnoreCase(shapeType)) {
            Rectangle rectangle = new Rectangle();
            rectangle.x = x;
            rectangle.y = y;
            rectangle.width = 20; // Default width for simplicity
            rectangle.height = 30; // Default height for simplicity
            rectangle.color = color;
            return rectangle;
        }
        // Default to null if type is not recognized
        return null;
    }

    private List<Shape> cloneAndCompare(List<Shape> shapes) {
        List<Shape> shapesCopy = new ArrayList<>();
        for (Shape shape : shapes) {
            shapesCopy.add(shape.clone());
        }
        return shapesCopy;
    }
}
