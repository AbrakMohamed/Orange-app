package com.example.userservice;

import com.example.userservice.entity.Champ;
import com.example.userservice.entity.Formulaire;
import com.example.userservice.entity.Reponse;

import com.example.userservice.repository.ChampRepository;
import com.example.userservice.repository.FormulaireRepository;
import com.example.userservice.service.ExcelService;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootTest
class UserserviceApplicationTests {

	@Autowired
	FormulaireRepository fr;
	@Autowired
	ChampRepository ch;

	@Autowired
	ExcelService es;





	@Test
	public void TestreadDataFromExcel() throws IOException {
		es.readDataFromExcel("C:\\Users\\Test\\Desktop\\datacollection\\userservice\\src\\test\\java\\com\\example\\userservice\\testCollection.xlsx");
	}

	@Test
	public void TestAddDataFromExcelToDatabase() throws IOException{
		es.writeDataFromExcelToDatabase("C:\\Users\\Test\\Desktop\\datacollection\\userservice\\src\\test\\java\\com\\example\\userservice\\testCollection.xlsx");
	}







}
