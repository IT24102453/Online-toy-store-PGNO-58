package com.toymanagement.servlet;

import com.toymanagement.model.Toy;
import com.toymanagement.util.FileHandler;
import com.toymanagement.LinkedList.ToyLinkedList;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/manageToys")
public class ManageToysServlet extends HttpServlet {
    private ToyLinkedList toyInventory;

    @Override
    public void init() throws ServletException {
        try {
            toyInventory = new ToyLinkedList();
            List<Toy> toysFromFile = FileHandler.readToys();
            toyInventory.addAll(toysFromFile);
        } catch (IOException e) {
            throw new ServletException("Failed to initialize toy inventory", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        try {
            if ("Add Toy".equals(action)) {
                handleAddToy(request, response);
            } else if ("View Toys".equals(action)) {
                handleViewToys(request, response);
            } else if ("Sort by Age Group".equals(action)) {
                handleSortByAgeGroup(request, response);
            } else if ("Delete".equals(action)) {
                handleDeleteToy(request, response);
            } else if ("Update".equals(action)) {
                handleUpdateToyForm(request, response);
            } else if ("Update Toy".equals(action)) {
                handleUpdateToy(request, response);
            } else if ("View Orders".equals(action)) {
                handleViewOrders(request, response);
            } else if ("View Payments".equals(action)) {
                handleViewPayments(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("error", "An error occurred: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    private void handleAddToy(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String toyName = request.getParameter("toyName");
        String ageGroup = request.getParameter("ageGroup");
        String toyPriceStr = request.getParameter("toyPrice");
        String category = request.getParameter("category");

        if (toyName == null || toyName.trim().isEmpty() || ageGroup == null || ageGroup.trim().isEmpty()) {
            request.setAttribute("message", "Toy name and age group cannot be empty.");
            request.getRequestDispatcher("manageToys.jsp").forward(request, response);
            return;
        }

        double toyPrice;
        try {
            toyPrice = Double.parseDouble(toyPriceStr);
            if (toyPrice <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            request.setAttribute("message", "Invalid toy price. Must be a positive number.");
            request.getRequestDispatcher("manageToys.jsp").forward(request, response);
            return;
        }

        if (category == null || category.trim().isEmpty()) {
            request.setAttribute("message", "Category cannot be empty.");
            request.getRequestDispatcher("manageToys.jsp").forward(request, response);
            return;
        }

        Toy toy = new Toy(toyName, ageGroup, toyPrice, category);
        toyInventory.add(toy);
        FileHandler.writeToys(toyInventory.toList()); // Convert to List<Toy>
        request.setAttribute("message", "Toy added successfully!");
        request.getRequestDispatcher("manageToys.jsp").forward(request, response);
    }

    private void handleViewToys(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String filterCategory = request.getParameter("filterCategory");

        if (filterCategory != null && !filterCategory.isEmpty()) {
            ToyLinkedList filteredToys = new ToyLinkedList();
            for (int i = 0; i < toyInventory.size(); i++) {
                Toy toy = toyInventory.get(i);
                if (toy.getCategory().equalsIgnoreCase(filterCategory)) {
                    filteredToys.add(toy);
                }
            }
            request.setAttribute("toys", filteredToys.toList());
        } else {
            request.setAttribute("toys", toyInventory.toList());
        }
        request.getRequestDispatcher("toyInventory.jsp").forward(request, response);
    }


    private void handleSortByAgeGroup(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        selectionSortByAgeGroup(toyInventory);
        FileHandler.writeToys(toyInventory.toList());
        response.sendRedirect("manageToys?action=View Toys");
    }

    private void handleDeleteToy(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int index = Integer.parseInt(request.getParameter("index"));
        toyInventory.remove(index);
        FileHandler.writeToys(toyInventory.toList());
        request.setAttribute("toys", toyInventory.toList());
        request.setAttribute("message", "Toy deleted successfully!");
        request.getRequestDispatcher("toyInventory.jsp").forward(request, response);
    }

    private void handleUpdateToyForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int index = Integer.parseInt(request.getParameter("index"));
        Toy toy = toyInventory.get(index);
        request.setAttribute("toy", toy);
        request.setAttribute("index", index);
        request.getRequestDispatcher("updateToy.jsp").forward(request, response);
    }

    private void handleUpdateToy(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int index = Integer.parseInt(request.getParameter("index"));
        String toyName = request.getParameter("toyName");
        String ageGroup = request.getParameter("ageGroup");
        String toyPriceStr = request.getParameter("toyPrice");
        String category = request.getParameter("category");

        if (toyName == null || toyName.trim().isEmpty() || ageGroup == null || ageGroup.trim().isEmpty()) {
            request.setAttribute("message", "Toy name and age group cannot be empty.");
            request.getRequestDispatcher("updateToy.jsp").forward(request, response);
            return;
        }

        double toyPrice;
        try {
            toyPrice = Double.parseDouble(toyPriceStr);
            if (toyPrice <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            request.setAttribute("message", "Invalid toy price. Must be a positive number.");
            request.getRequestDispatcher("updateToy.jsp").forward(request, response);
            return;
        }

        if (category == null || category.trim().isEmpty()) {
            request.setAttribute("message", "Category cannot be empty.");
            request.getRequestDispatcher("updateToy.jsp").forward(request, response);
            return;
        }

        Toy updatedToy = new Toy(toyName, ageGroup, toyPrice, category);
        toyInventory.set(index, updatedToy);
        FileHandler.writeToys(toyInventory.toList());
        request.setAttribute("toys", toyInventory.toList());
        request.setAttribute("message", "Toy updated successfully!");
        request.getRequestDispatcher("toyInventory.jsp").forward(request, response);
    }

    private void handleViewOrders(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("orders", FileHandler.readOrders());
        request.getRequestDispatcher("userOrders.jsp").forward(request, response);
    }

    private void handleViewPayments(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("payments", FileHandler.readPayments());
        request.getRequestDispatcher("userPayments.jsp").forward(request, response);
    }

    private void selectionSortByAgeGroup(ToyLinkedList toys) {
        int n = toys.size();
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (toys.get(j).getAge() < toys.get(minIndex).getAge()) {
                    minIndex = j;
                }
            }
            Toy temp = toys.get(minIndex);
            toys.set(minIndex, toys.get(i));
            toys.set(i, temp);
        }
    }
}
