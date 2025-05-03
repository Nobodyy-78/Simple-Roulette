package app.model;

public class Model {

	private int credito;
	private int lancio;
	private boolean spinning;
	
	public Model() {
		this.lancio = 0;
		this.spinning = false;
	}

	
	public boolean isSpinning() {
		return spinning;
	}


	public void setSpinning(boolean spinning) {
		this.spinning = spinning;
	}


	public int getCredito() {
		return credito;
	}

	
	public void setCredito(int credito) {
		this.credito = credito;
	}

	
	public int getLancio() {
		return lancio;
	}
	
	
	public void setLancio(int lancio) {
		this.lancio = lancio;
	}

	
	public void increaseLancio() {
		this.lancio++;
	}
	
	public void decreaseLancio() {
		if (this.lancio != 0)
			this.lancio--;
	}
}
