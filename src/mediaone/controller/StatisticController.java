package mediaone.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;

import mediaone.service.BillServiceImpl;
import mediaone.view.MainUI;
import mediaone.view.StatisticView;

public class StatisticController {
	private MainUI mainUI;
	private StatisticView statisticView;
	private BillServiceImpl billServiceImpl;
	private JButton btnStatistic;
	
	public StatisticController(MainUI mainUI) {
		this.mainUI = mainUI;
		billServiceImpl = new BillServiceImpl();
		btnStatistic = mainUI.getManagerBill().getButtonBillView().getBtnStatistic();
		
		btnStatisticEvent();
	}
	
	/*
	 * Handle event for btnStatisstic on Manager Bill Panel
	 */
	private void btnStatisticEvent() {
		btnStatistic.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				statisticView = new StatisticView(mainUI);
				statisticView.setVisible(true);
				actionOnStatisticView();
			}
		});
	}
	
	/*
	 * Handle event on statisticView
	 */
	private void actionOnStatisticView() {
		JButton btnShowProfit = statisticView.getBtnStatistic();
		JButton btnCancel = statisticView.getBtnCancel();
		
		btnShowProfit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				String startDay = statisticView.getCbStartDay().getSelectedItem().toString() + "-"
								+ statisticView.getCbStartMonth().getSelectedItem().toString() + "-"
								+ statisticView.getCbStartYear().getSelectedItem().toString();
				String endDay   = statisticView.getCbEndDay().getSelectedItem().toString() + "-"
								+ statisticView.getCbEndMonth().getSelectedItem().toString() + "-"
								+ statisticView.getCbEndYear().getSelectedItem().toString();
				LocalDate startDate = LocalDate.parse(startDay, formatter);
				LocalDate endDate = LocalDate.parse(endDay, formatter);
				
				Double profit = billServiceImpl.getProfit(startDate, endDate);
				int numbOfBill = billServiceImpl.getNoOfBills(startDate, endDate);
				statisticView.getLbMoney().setText(profit + "");
				statisticView.getLbNumberOfBill().setText(numbOfBill + "");
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				statisticView.setVisible(false);
			}
		});
	}
	
	
}
