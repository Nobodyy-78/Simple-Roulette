package app.control;

import javax.swing.JFrame;
import javax.swing.JToggleButton;

import app.model.Model;
import app.view.Finestra;
import app.view.Help;

public class Controller {

	private Finestra view;
	private Model model;
	
	//il costruttore del Model
	public Controller(Model model) {
		this.model = model;
		this.view = new Finestra(this);
		view.setVisible(true);
	}
	
	//Prende il lancio
	public String getLancio() {
		return Integer.toString(model.getLancio());
	}
	
	//Prende il credito
	public String getCredito() {
		return Integer.toString(model.getCredito());
	}
	
	//Funzione che incrementa la giocata (i soldi da scommettere)
	public void incrementa() {
		if(!model.isSpinning()) {
			model.increaseLancio();
			view.updateLancio(getLancio());
		}
	}
	
	//Funzione che decrementa la giocata (i soldi da scommettere)
	public void decrementa() {
		if(!model.isSpinning()) {
			model.decreaseLancio();
			view.updateLancio(getLancio());
		}
	}
	
	//Funzione per iniziare il gioco con un Thread
	public void startRoulette() {
		if (!model.isSpinning()) {
		
			model.setSpinning(true);
			int nEventi = 0;
			for (JToggleButton btn : view.numberButtons) {
				if (btn.isSelected())
					nEventi++;
			}
			
			disable();
			
			model.setCredito(model.getCredito() - model.getLancio()*nEventi);
			view.updateCredito(Integer.toString(model.getCredito()));
	        int number = (int) (Math.random()*37);

	        new Thread(() -> {
	            try {
	                int fullRounds = 3; // giri completi minimi
	                int totalSteps = fullRounds * 37 + number; // passo finale sul numero
	                long totalDuration = 4000; // 4 secondi di animazione

	                int currentIndex = 0; //indice per i led

	                for (int step = 0; step <= totalSteps; step++) {
	                    accendiLED(currentIndex);
	                    currentIndex = (currentIndex + 1) % 37;

	                    double t = step / (double) totalSteps;
	                    double easedT = rallentatore(t);
	                    long delay = (long) (easedT * totalDuration / totalSteps);

	                    Thread.sleep(delay);
	                }

	                accendiLED(number);		//accendo solo il numero vincente
	                checkWin(number);		//controllo sempre che abbia vinto
	                enable();
	    	        model.setSpinning(false);

	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }).start();
		}
    }
	
	//Funzione per accendere i LED
	private void accendiLED(int index) {
		
        for (int i = 0; i < view.leds.length; i++) {
            view.leds[i].nascondiCerchioGiallo();
        }
        view.leds[index].mostraCerchioGiallo();
    }
	
	//Funzione per rallentare
	private double rallentatore(double t) {
        return 1 - Math.pow(1 - t, 3);
    }
	
	//Funzione per disabilitare i ToggleButtons e l'uscita
	public void disable() {
		for (JToggleButton btn : view.numberButtons) {
			btn.setEnabled(false);
		}
		view.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}
	
	//Funzione per abilitare i ToggleButtons e l'uscita
	public void enable() {
		for (JToggleButton btn : view.numberButtons) {
			btn.setEnabled(true);
		}
		view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	//Funzione che controlla se l'utente ha azzeccato il numero
	private void checkWin(int number) {
		
		for (JToggleButton btn : view.numberButtons) {
			if (btn.isSelected() && Integer.parseInt(btn.getText()) == number) {
				model.setCredito(model.getLancio()*36 + model.getCredito()); //aggiungo i soldi
				model.setLancio(0); //azzero la giocata
				
				//aggiorno la view
				view.updateLancio(getLancio());
				view.updateCredito(getCredito());
			}
		}
	}
	
	//Funzione che apre la finestra Help
	public void openHelp() {
		Help window = new Help();
		window.setVisible(true);
	}
	
	public boolean isSpinning() {
		return model.isSpinning();
	}
	
	public void avvia() {
		view.setVisible(true);
	}
}
