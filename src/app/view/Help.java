package app.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.ScrollPane;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Help extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblTitolo;
	private String contenuto;
	private ScrollPane scrollPane;
	private JTextArea textArea;

	public Help() {
		ImageIcon icon = new ImageIcon(getClass().getResource("/img/helpIcon.png"));
        setIconImage(icon.getImage());
        setTitle("aiuto rapido");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		lblTitolo = new JLabel("Piccola guida sulla Simple Roulette");
		lblTitolo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitolo.setFont(new Font("MV Boli", Font.PLAIN, 25));
		contentPane.add(lblTitolo, BorderLayout.NORTH);
		
		contenuto = getTesto();		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setText(contenuto);
		textArea.setFont(new Font("MV Boli", Font.PLAIN, 16));
		
		scrollPane = new ScrollPane();
		scrollPane.add(textArea);
		contentPane.add(scrollPane, BorderLayout.CENTER);
	}
	
	
	private String getTesto() {
		 // Ottiene il classloader della classe corrente
        ClassLoader classLoader = Help.class.getClassLoader();

        // Legge il file dal percorso relativo nel classpath
        try (InputStream is = classLoader.getResourceAsStream("hlp/aiuto.txt")) {
            if (is == null) {
                throw new IllegalArgumentException("File 'hlp/aiuto.txt' non trovato!");
            }

            // Converte il contenuto del file in una stringa
            return new BufferedReader(new InputStreamReader(is))
                    .lines()
                    .collect(Collectors.joining("\n"));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
	}
}
