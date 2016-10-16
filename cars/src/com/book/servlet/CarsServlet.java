package com.book.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.dao.CarDAO;
import com.book.model.Car;
import com.book.model.CarsList;
import com.book.model.Response;
import com.book.utils.RegexUtil;
import com.book.utils.ServletUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class CarsServlet
 */
@WebServlet("/cars/*")
public class CarsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CarDAO carDAO;
	CarsList list;
	Car car;
	Response response;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CarsServlet() {
        super();
        
        // TODO Auto-generated constructor stub
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		carDAO = new CarDAO();
    	car = getCarFromrequest(req);
    	
    	if(car.getId() != 0){
    		carDAO.update(car);    		
    	} else {
    		car.setId(carDAO.insert(car));
    	}
    	
		// Turn the object into JSON 
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String stringCarJson = gson.toJson(car);
		
		// Write the json on Servlet response with application/json
		ServletUtil.writeJSON(resp, stringCarJson);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestUri = request.getRequestURI();
		Integer id = RegexUtil.matchId(requestUri);
		
		if(id != null){
			carDAO = new CarDAO();
			car = carDAO.getCarById(id);
			
			if(car != null){
				// Turn the object into JSON 
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				String stringCarJson = gson.toJson(car);
				
				// Write the json on Servlet response with application/json
				ServletUtil.writeJSON(response, stringCarJson);
			} else {
				response.sendError(404, "Car not found!");
			}
			
		} else {
			carDAO = new CarDAO();
			List<Car> carsList = carDAO.getCarsList();
			list = new CarsList();
			list.setCars(carsList);
			
			// Turn the object into JSON 
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String stringListCarJson = gson.toJson(list);
			
			// Write the json on Servlet response with application/json
			ServletUtil.writeJSON(response, stringListCarJson);
			
		}	
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String requestUri = req.getRequestURI();
		Integer id = RegexUtil.matchId(requestUri);
		carDAO = new CarDAO();
		
		if(id != null){
			carDAO.delete(id);
			response = Response.ok("Car removed successfully");
			
			// Turn the object into JSON 
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String stringListCarJson = gson.toJson(response);
			
			// Write the json on Servlet response with application/json
			ServletUtil.writeJSON(resp, stringListCarJson);
			
		} else {
			response = Response.error("Invalid URL");
			
			// Turn the object into JSON 
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String stringListCarJson = gson.toJson(response);
			
			// Write the json on Servlet response with application/json
			ServletUtil.writeJSON(resp, stringListCarJson);
		}
	}

	private Car getCarFromrequest(HttpServletRequest request) {
		Car car = new Car();
		String id = request.getParameter("id");
		
		if(id != null){
			car = carDAO.getCarById(Integer.parseInt(id));
		}
		
		car.setName(request.getParameter("name"));
		car.setDescription(request.getParameter("description"));
		car.setUrlPhoto(request.getParameter("url_photo"));
		car.setUrlVideo(request.getParameter("url_video"));
		car.setLatitud(request.getParameter("latitud"));
		car.setLongitud(request.getParameter("longitud"));
		car.setType(request.getParameter("type"));
		
		return car;
	}
	
}
