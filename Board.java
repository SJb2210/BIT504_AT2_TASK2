import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Board implements MouseListener {
	//game window
	//define variables
	int boardWidth = 600;
	int boardHeight = 650; //50px for the status label on the bottom
	
	//create the window, panel, board and status bar
	JFrame frame = new JFrame();
	JLabel textLabel = new JLabel();
	JPanel textPanel = new JPanel();
	JPanel boardPanel = new JPanel();
	JButton[][] board = new JButton[3][3]; //2D array to keep track of placement of cells
	                                      //use this array to check if there is winner or not
	
	// for each button there will be text of X or O
	String playerX = "X";                    
	String playerO = "O";
	String currentPlayer = playerX;  //first player will be X
	
	boolean gameOver = false;
	int turns = 0;
	
	
	//add a constructor
	Board() {
		// main window
		frame.setTitle("Tic Tac Toe");
		frame.setVisible(true);
		frame.setSize(boardWidth, boardHeight);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		// Status bar
		textLabel.setBackground(Color.gray);
		textLabel.setForeground(Color.white);
		textLabel.setFont(new Font("Arial", Font.BOLD, 30));
		textLabel.setHorizontalAlignment(JLabel.LEFT);
		textLabel.setText("X goes first");
		textLabel.setOpaque(true);
		
		// the Panel for the Board
		textPanel.setLayout(new BorderLayout());
		//add the status bar to the panel
		textPanel.add(textLabel);
		//add the panel to the frame
		frame.add(textPanel, BorderLayout.SOUTH);
		
		// the playing Board
		boardPanel.setLayout(new GridLayout(3,3,7,7));
		boardPanel.setBackground(Color.gray);
		frame.add(boardPanel);
		
		// add the cells
		for (int r = 0; r < 3; r++) {  // r = row
			for (int c = 0; c < 3; c++) { // c = column
				JButton cell = new JButton();
				board[r][c] = cell;
				boardPanel.add(cell);  //adding the cells to the board
				
				// adding properties to the cells
				cell.setBackground(Color.white);
				cell.setForeground(Color.BLUE);
				cell.setFont(new Font("Arial", Font.BOLD,120));
				cell.setFocusable(false);
				// cell.setText(currentPlayer);
				
				//set the cells up so that when clicking on a cell text is set to either X or O
				// to do that a MouseListener needs to be added to each cell
				cell.addMouseListener(this);
			}
		}
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		
		if (gameOver)return;
		JButton cell = (JButton) e.getSource();
		if(cell.getText()== "") {
			cell.setText(currentPlayer);
			if(cell.getText().toString().equals("O")) {
				cell.setForeground(Color.red);
			}
			turns++;
			checkWinner();
			if (!gameOver) {
			currentPlayer = currentPlayer == playerX ? playerO : playerX;
			textLabel.setText(currentPlayer + " 's turn.");	
			}
		}
		if (gameOver) {
			resetGame(cell);
			
				}
				
			}
		
		
	
	
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	
	//define the checkWinner function
	void checkWinner() {
		//horizontal
		for (int r = 0; r < 3; r++) {
			if(board[r][0].getText()== "") continue;
			
			if(board[r][0].getText() == board[r][1].getText() &&
				board[r][0].getText() == board[r][2].getText()) {
				for (int i = 0; i < 3; i++) {
					setWinner(board[r][i]);
				}
				gameOver = true;
				return;
			}
		}
		//vertical
		for (int c = 0; c < 3; c++) {
			if(board[0][c].getText()== "") continue;
			
			if(board[0][c].getText() == board[1][c].getText() &&
					board[1][c].getText() == board[2][c].getText()) {
					for (int i = 0; i < 3; i++) {
						setWinner(board[i][c]);
					}
					gameOver = true;
					return;
		}
	}
		//diagonally
			if(board[0][0].getText() == board[1][1].getText() &&
				board[1][1].getText() == board[2][2].getText() &&
				board[0][0].getText() != "") {
					for (int i = 0; i < 3; i++) {
						setWinner(board[i][i]);
					}
					gameOver = true;
					return;
			}
			//anti-diagonally
			if(board[0][2].getText() == board[1][1].getText() &&
					board[1][1].getText() == board[2][0].getText() &&
					board[0][2].getText() != "") {
					setWinner(board[0][2]);
					setWinner(board[1][1]);
					setWinner(board[2][0]);
					gameOver = true;
					return;
			}
			if (turns == 9) {
				for (int r = 0; r < 3; r++) {
					for (int c = 0; c < 3; c++) {
						isDraw(board[r][c]);
					}
				}
				gameOver = true;
				return;
			}
	}
	// define the setWinner function
	void setWinner(JButton cell) {
		cell.setForeground(Color.green);
		cell.setBackground(Color.gray);
		textLabel.setText(currentPlayer + " is the winner! Click to play again.");
		
	}
	void isDraw(JButton cell) {
		cell.setForeground(Color.orange);
		cell.setBackground(Color.gray);
		textLabel.setText("It's a Draw! Click to play again");
		
	}
	void resetGame(JButton cell) {
		if (turns == 10) {
			for (int r = 0; r < 3; r++) {
				for (int c = 0; c < 3; c++) {
					board [r][c] = cell ;
						cell.setText(currentPlayer);
					
				}
		}
		}
	}
}
		