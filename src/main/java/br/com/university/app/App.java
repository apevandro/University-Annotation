package br.com.university.app;

import br.com.university.utils.Hql;
import br.com.university.utils.Utils;

public class App {

	public static void main(String[] args) {

		Utils utils = new Utils();
		
		utils.insertion();          // insertion can also be done using insertion.txt file commands

		Hql query = new Hql();
		query.questionA();
	/*	query.questionB();
		query.questionB_streamVersion();
		query.questionC();
		query.questionD();
		query.questionD_streamVersion();
		query.questionE();
		query.questionE_streamVersion();
		query.questionF();
		query.questionF_streamVersion();
		query.questionG();
		query.questionH();
		query.questionI();
		query.questionJ();
		query.questionK();
		query.questionL();
		query.questionM();
		query.questionN();
		query.questionO();
		query.questionP();
		query.questionQ();
		query.questionR();
		query.questionS();
		query.questionT();
		query.questionU();
		query.questionV();
		query.questionW();
		query.questionX();
		query.questionY();
		query.questionZ(); */
	}

}