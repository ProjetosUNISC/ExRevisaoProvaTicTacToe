package view;

import model.ControleJogoVelha;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmJogoVelha extends JFrame {

    ControleJogoVelha modelo = new ControleJogoVelha();

    //PAINES
    private JPanel painelPrincipal = new JPanel();
    private JPanel painelBotoes = new JPanel();
    private JPanel painelTabuleiro = new JPanel();
    private JPanel painelStatus = new JPanel();

    //BOTOES E RADIOB
    private JButton botoes[];
    private JButton btnNovo;
    private JRadioButton rbX, rb0;
    private JTextArea txt;


    //INICIA O FRAME
    public FrmJogoVelha() {


        super("Jogo Velha");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setSize(400, 400);

        //PAINEL INICIO CONF
        btnNovo = new JButton("Novo Jogo");
        btnNovo.addActionListener(new Eventos());
        rbX = new JRadioButton("Jogador X", true);
        rb0 = new JRadioButton("Jogador O");
        rbX.setEnabled(false);
        rb0.setEnabled(false);
        //ADD RADIO NO GRUPO
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(rbX);
        buttonGroup.add(rb0);

        //COLOCA NO PAINEL E CONF ELE
        painelBotoes.setLayout(new FlowLayout());
        painelBotoes.add(btnNovo);
        painelBotoes.add(rbX);
        painelBotoes.add(rb0);


        //PARTE DO MEIO DO JOGO DA VELHA
        painelTabuleiro.setLayout(new GridLayout(3, 3));
        botoes = new JButton[9];
        for (int i = 0; i < 9; i++) {
            botoes[i] = new JButton(Integer.toString(i + 1));
            botoes[i].addActionListener(new Eventos());
            painelTabuleiro.add(botoes[i]);
        }

        //PAINEL DE BAIXO
        painelStatus.setLayout(new FlowLayout());
        txt = new JTextArea(2, 30);
        txt.setEditable(false);
        painelStatus.add(txt);


        //ADICIONA PAINEL NA POSIÇÕES DO PAINEL PRINCIPAL
        painelPrincipal.setLayout(new BorderLayout());
        painelPrincipal.add(painelBotoes, BorderLayout.NORTH);
        painelPrincipal.add(painelTabuleiro, BorderLayout.CENTER);
        painelPrincipal.add(painelStatus, BorderLayout.SOUTH);

        //ADD PAINEL DO FRAME
        this.add(painelPrincipal, BorderLayout.CENTER);
    }


    //CLASSE EVENTOS
    public class Eventos implements ActionListener {
        public void actionPerformed(ActionEvent e) {


            if (e.getSource() == btnNovo) {
                modelo.iniciaPartida();
                for (int i = 0; i < botoes.length; i++) {
                    botoes[i].setEnabled(true);
                    botoes[i].setText(Integer.toString(i + 1));
                }
                rbX.setSelected(true);
                txt.setText("Partida iniciada");
            } else {
                int pos = Integer.parseInt(e.getActionCommand()) - 1;
                Character letra;

                if (rbX.isSelected()) {
                    letra = 'X';
                    rb0.setSelected(true);
                } else {
                    letra = 'O';
                    rbX.setSelected(true);
                }

                modelo.adicionaMovimento(letra, pos);
                botoes[pos].setEnabled(false);
                botoes[pos].setText(letra.toString());

                //pega texto pela
                txt.setText(modelo.getStatusPartida());

                if (modelo.avaliaPartida() != 0) {
                    for (JButton botao : botoes) {
                        botao.setEnabled(false);
                    }
                }
            }
        }
    }
}
