package app.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import app.control.Controller;
import app.model.JLed;

public class Finestra extends JFrame {

	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private Controller c;
	private JPanel contentPane;
	public JLed[] leds = new JLed[37];
	public JToggleButton[] numberButtons = new JToggleButton[37];
	private JLabel lblTitle;
	private JLabel lblTextCredito;
	private JLabel lblCredito;
	private JLabel lblTextLancio;
	private JLabel lblLancio;
	private JButton btnDecrease;
	private JButton btnIncrease;
	private JLabel lblNumbers;
	private JButton btnStart;
	private JLabel lblNumbers1;
	private JButton btnHelp;
	
	public Finestra(Controller c) {
		ImageIcon icon = new ImageIcon(getClass().getResource("/img/rouletteIcon.png"));
		setIconImage(icon.getImage());
		
		this.c = c;
		
		setBackground(new Color(255, 255, 255));
		setTitle("Simple Roulette!!!");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 850, 500);
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 91, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		
		//variabili per la creazione della corona di LED
		int centerX = 200;
        int centerY = 200;
        int radius = 150;

        //creazione dei LED disposti in cerchio
        for (int i = 0; i < leds.length; i++) {
        	
        	//angolo per ogni LED (37 in tutto)
            double angle = 2 * Math.PI * i / leds.length;
            
            int x = (int) (centerX + radius * Math.cos(angle)) - 20;
            int y = (int) (centerY + radius * Math.sin(angle)) - 20;

            leds[i] = new JLed(setColor(i),String.valueOf(i));
            leds[i].setBounds(x, y, 20, 20);
            leds[i].setOpaque(true);
            leds[i].setBackground(new Color(0, 91, 0));
            leds[i].setForeground(Color.WHITE);
            
            contentPane.add(leds[i]);
        }
        
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		//Label per il Titolo "Simple Roulette"
		lblTitle = new JLabel("Simple Roulette");
		lblTitle.setForeground(new Color(255, 0, 0));
		lblTitle.setFont(new Font("MV Boli", Font.PLAIN, 38));
		lblTitle.setBounds(437, 28, 322, 72);
		
		contentPane.add(lblTitle);
		
		//creazione del JPanel ospitante i bottoni per il "bet" controllato
		JPanel tabella = new JPanel(new GridLayout(6, 7, 4, 4));
		tabella.setBounds(400, 100, 400, 250);; //offset che non vada sopra ad altri componenti
		tabella.setBackground(new Color(0, 91, 0));   //classico colore
        tabella.setPreferredSize(new Dimension(150, 150));  //fissa le dimensioni della griglia
        
        for (int i = 0; i < leds.length; i++) {
            numberButtons[i] = new JToggleButton(String.valueOf(i));	//contengono i valori dei LED
            numberButtons[i].setPreferredSize(new Dimension(25, 25));	// Imposta la dimensione 25x25
            numberButtons[i].setRequestFocusEnabled(false);
            numberButtons[i].setFont(new Font("SansSerif", Font.PLAIN, 12)); 
            numberButtons[i].setBackground(setColor(i));	//scelta delle caselle rosse e nere
            numberButtons[i].setForeground(Color.WHITE);
            tabella.add(numberButtons[i]);
        }
        
        
        //riempie gli spazi vuoti
        while (tabella.getComponentCount() < 42) {
            tabella.add(new JLabel());
        }
        
        //aggiunta del pannello della tabella al main panel
        contentPane.add(tabella);
        
        lblTextCredito = new JLabel("Credito:");
        lblTextCredito.setForeground(new Color(255, 0, 0));
        lblTextCredito.setFont(new Font("MV Boli", Font.PLAIN, 25));
        lblTextCredito.setBounds(501, 370, 147, 29);
        contentPane.add(lblTextCredito);
        
        lblCredito = new JLabel(c.getCredito());
        lblCredito.setHorizontalAlignment(SwingConstants.RIGHT);
        lblCredito.setForeground(new Color(255, 0, 0));
        lblCredito.setFont(new Font("Monospaced", Font.BOLD, 25));
        lblCredito.setBounds(614, 369, 107, 30);
        contentPane.add(lblCredito);
        
        lblTextLancio = new JLabel("Puntata:");
        lblTextLancio.setForeground(Color.RED);
        lblTextLancio.setFont(new Font("MV Boli", Font.PLAIN, 25));
        lblTextLancio.setBounds(501, 409, 147, 29);
        contentPane.add(lblTextLancio);
        
        lblLancio = new JLabel(c.getLancio());
        lblLancio.setHorizontalAlignment(SwingConstants.RIGHT);
        lblLancio.setForeground(Color.RED);
        lblLancio.setFont(new Font("Monospaced", Font.BOLD, 25));
        lblLancio.setBounds(614, 409, 107, 30);
        contentPane.add(lblLancio);
        
        btnIncrease = new JButton("1+");
        btnIncrease.setFont(new Font("MV Boli", Font.PLAIN, 15));
        btnIncrease.setRequestFocusEnabled(false);
        btnIncrease.setForeground(new Color(255, 255, 255));
        btnIncrease.setBounds(377, 409, 85, 21);
        btnIncrease.setBackground(Color.BLACK);
        contentPane.add(btnIncrease);
        btnIncrease.addActionListener((e) -> c.incrementa());
        
        btnDecrease = new JButton("1-");
        btnDecrease.setFont(new Font("MV Boli", Font.PLAIN, 15));
        btnDecrease.setRequestFocusEnabled(false);
        btnDecrease.setForeground(new Color(255, 255, 255));
        btnDecrease.setBackground(new Color(255, 0, 0));
        btnDecrease.setBounds(377, 378, 85, 21);
        contentPane.add(btnDecrease);
        btnDecrease.addActionListener((e) -> c.decrementa());
        
        lblNumbers = new JLabel(".00 €");
        lblNumbers.setHorizontalAlignment(SwingConstants.LEFT);
        lblNumbers.setForeground(Color.RED);
        lblNumbers.setFont(new Font("Monospaced", Font.BOLD, 25));
        lblNumbers.setBounds(725, 408, 85, 30);
        contentPane.add(lblNumbers);
        
        btnStart = new JButton("Gamble!!!");
        btnStart.setForeground(new Color(255, 255, 255));
        btnStart.setRequestFocusEnabled(false);
        btnStart.setBackground(new Color(255, 0, 0));
        btnStart.setFont(new Font("MV Boli", Font.PLAIN, 25));
        btnStart.setBounds(96, 368, 181, 70);
        contentPane.add(btnStart);
        btnStart.addActionListener((e) -> c.startRoulette());
        
        lblNumbers1 = new JLabel(".00 €");
        lblNumbers1.setHorizontalAlignment(SwingConstants.LEFT);
        lblNumbers1.setForeground(Color.RED);
        lblNumbers1.setFont(new Font("Monospaced", Font.BOLD, 25));
        lblNumbers1.setBounds(725, 369, 85, 30);
        contentPane.add(lblNumbers1);
        
        //bottone rapido per la guida
        ImageIcon icon2 = new ImageIcon(getClass().getResource("/img/helpButton.png"));
        btnHelp = new JButton("", icon2);
        btnHelp.setRequestFocusEnabled(false);
        btnHelp.setToolTipText("guida rapida");
        btnHelp.setBorderPainted(false);
        btnHelp.setBackground(new Color(0, 91, 0));
        btnHelp.setFont(new Font("Tahoma", Font.PLAIN, 9));
        btnHelp.setBounds(0, 442, 20, 21);
        contentPane.add(btnHelp);
        btnHelp.addActionListener((e) -> c.openHelp());
        
	}
	
	//Funzione per la scelta del colore
	private Color setColor(int index) {
		
		if (index == 0)
			return new Color(0, 219, 28);  //il colore dell zero verde
		else if (index % 2 == 0)
			return Color.RED;	//caselle pari = rosse
		else
			return Color.BLACK; //caselle dispari = nere
	}
	
	public void updateLancio(String money) {
		lblLancio.setText(money);
	}
	
	public void updateCredito(String money) {
		lblCredito.setText(money);
	}
}
