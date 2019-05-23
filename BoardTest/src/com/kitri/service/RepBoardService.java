package com.kitri.service;

import java.sql.SQLException;

import com.kitri.dao.RepBoardDAO;
import com.kitri.dto.RepBoard;

public class RepBoardService {

	private RepBoardDAO boardDAO;
	
	public RepBoardService() {
		boardDAO = new RepBoardDAO();
	}
	
	
	public void write(RepBoard repBoard) throws ClassNotFoundException, SQLException {
		repBoard.setParent_seq(0);
		boardDAO.insert(repBoard);
	}
	
	public void reply(RepBoard repBoard) throws Exception {
		if (repBoard.getParent_seq() == 0) {
			throw new Exception("No exist parent board sequence");
		}
		
		boardDAO.insert(repBoard);
	}
}
