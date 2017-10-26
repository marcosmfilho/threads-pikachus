import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Semaphore;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;


public class menuSO extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static int value;

	public menuSO() {
				
		setBackground(SystemColor.inactiveCaption);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 438, 390);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setForeground(Color.WHITE);
		contentPane.setBackground(new Color(0, 102, 153));
		contentPane.setBorder(new EmptyBorder(2, 2, 2, 2));
		setContentPane(contentPane);
		
		contentPane.setLayout(null);
		
		JLabel lblRollercoasterPikachu = new JLabel("RollerCoaster Pikachu");
		lblRollercoasterPikachu.setFont(new Font("MS PGothic", Font.BOLD, 25));
		lblRollercoasterPikachu.setForeground(Color.ORANGE);
		lblRollercoasterPikachu.setBounds(79, 49, 265, 33);
		contentPane.add(lblRollercoasterPikachu);
		
		SpinnerNumberModel model2 = new SpinnerNumberModel(0, 0, 10, 1); 
		JSpinner spinner = new JSpinner(model2);
		spinner.setBounds(325, 107, 41, 23);
		if ( spinner.getEditor() instanceof JSpinner.DefaultEditor ) {
		   JSpinner.DefaultEditor editor = ( JSpinner.DefaultEditor ) spinner.getEditor();
		   editor.getTextField().setEnabled( true );
		   editor.getTextField().setEditable( false );
		}
		contentPane.add(spinner);
		
		JLabel lblEscolhaAQuantidade = new JLabel("Escolha a quantidade de PIKACHUS");
		lblEscolhaAQuantidade.setFont(new Font("Gadugi", Font.BOLD, 15));
		lblEscolhaAQuantidade.setForeground(Color.WHITE);
		lblEscolhaAQuantidade.setBounds(50, 108, 265, 22);
		contentPane.add(lblEscolhaAQuantidade);
		
		JLabel lblTempoDeEmbarque = new JLabel("Velocidade dos passageiros");
		lblTempoDeEmbarque.setForeground(Color.WHITE);
		lblTempoDeEmbarque.setFont(new Font("Gadugi", Font.BOLD, 15));
		lblTempoDeEmbarque.setBounds(50, 141, 265, 22);
		contentPane.add(lblTempoDeEmbarque);
		
		JSpinner spinner_3 = new JSpinner();
		spinner_3.setBounds(325, 141, 41, 23);
		contentPane.add(spinner_3);
		
		JLabel lblQuantidadeDeVagas = new JLabel("Quantidade de vagas do vag\u00E3o");
		lblQuantidadeDeVagas.setForeground(Color.WHITE);
		lblQuantidadeDeVagas.setFont(new Font("Gadugi", Font.BOLD, 15));
		lblQuantidadeDeVagas.setBounds(50, 174, 265, 22);
		contentPane.add(lblQuantidadeDeVagas);
		
		SpinnerNumberModel model1 = new SpinnerNumberModel(1, 1, 6, 1); 
		JSpinner spinner_1 = new JSpinner(model1);
		spinner_1.setBounds(325, 180, 41, 23); 
		if ( spinner_1.getEditor() instanceof JSpinner.DefaultEditor ) {
			   JSpinner.DefaultEditor editor = ( JSpinner.DefaultEditor ) spinner_1.getEditor();
			   editor.getTextField().setEnabled( true );
			   editor.getTextField().setEditable( false );
		}
		contentPane.add(spinner_1);
		
		JLabel lblVelocidadeDoVago = new JLabel("Velocidade do vag\u00E3o");
		lblVelocidadeDoVago.setForeground(Color.WHITE);
		lblVelocidadeDoVago.setFont(new Font("Gadugi", Font.BOLD, 15));
		lblVelocidadeDoVago.setBounds(50, 208, 265, 22);
		contentPane.add(lblVelocidadeDoVago);
		
		JSpinner spinner_2 = new JSpinner();
		spinner_2.setBounds(325, 214, 41, 23);
		contentPane.add(spinner_2);
		
		JButton btnNewButton = new JButton("PLAY!");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
                 dispose();
				 menuSO.value = (int)spinner_1.getValue();
				 Wagon w = new Wagon((int)spinner_1.getValue(),(int)spinner_2.getValue());
			     Game.semaphoreWagon = new Semaphore(0);
			     w.start();
			    
			     Game.addPassengers((int)spinner.getValue(), (int)spinner_3.getValue());
			     Game.startPassengers();
			     
			     Game.semaphorePassenger = new Semaphore(Wagon.getSeats());
			     Game.semaphoreFila = new Semaphore(Game.fila.size());
			     Game.mutexIn = new Semaphore(1);
			     Game.mutexOut = new Semaphore(0);
			     
			     
			     Game game = new Game();
				 game.run();
			}
		});
		
		btnNewButton.setBounds(166, 294, 98, 23);
		contentPane.add(btnNewButton);
	}
}
