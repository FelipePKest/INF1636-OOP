package View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Model.*;
import Utils.*;

import java.awt.*;

public class Panel extends JPanel implements PieceObserver {
	
	public static final int TXT_X=120;
	public static final int TXT_Y=140;
	private int[][] visualPositions = new int[64][4];
	private Images piecesImages;
	
	JFrame frame;
	
	public Panel(MouseListener l) {
		// Cadastrando o Panel para que possa receber as notificações 
		this.piecesImages = new Images();
		this.setupFrame(l);
//		for (int[] visualPosition: visualPositions) {
//			System.out.println("Visual Positions: " + visualPosition[0] + visualPosition[1] + visualPosition[2] + visualPosition[3]);
//		}
	}
	
    private void setupFrame(MouseListener l)
    {
    	frame = new JFrame("Chess");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.setSize(600,620);
        frame.addMouseListener(l);
        frame.setVisible(true);
    }
	
	@Override
	public void paintComponent(Graphics g) {
	super.paintComponent(g);
		g.fillRect(0,0,600,600);
		for (int x = 0;x<600;x+=150) {
			for (int y = 0;y<600;y+=150) {
				g.clearRect(x, y, 75, 75);
			}
		}
		
		for (int x = 75;x<600;x+=150) {
			for (int y = 75;y<600;y+=150) {
				g.clearRect(x, y, 75, 75);
			}
		}
	}
	
	@Override
    public void paint(Graphics g)
    {
		System.out.println("chamei");
		super.paint(g);
        displayPieces(g);
    }
	
	public void notifyPositions(PieceObserved observed) {
		this.visualPositions = observed.getVisualPositions();
		this.repaint();		
	}
	
	public void displayPieces(Graphics g) {
		int i = 0;
		for (int[] piece: this.visualPositions) {			
			Image pieceImage = this.getPieceImage(piece[3], piece[2]);
			g.drawImage(pieceImage, i, 100, null);
			i+=10;
		}
	}
	
	private Image getPieceImage(int color, int type) {
		// Color == Black
		if (color == 0) {
			switch (type) {
			// PAWN
			case 0:
				return this.piecesImages.blackPawn;
			// KING
			case 1:
				return this.piecesImages.blackKing;
				
			// QUEEN
			case 2:
				return this.piecesImages.blackQueen;
				
			// KNIGHT
			case 3:
				return this.piecesImages.blackKing;
				
			// ROOK
			case 4:
				return this.piecesImages.blackRook;
				
			// BISHOP
			case 5:
				return this.piecesImages.blackBishop;
				
			}
		}
		// Color == White
		else {
			switch (type) {
			// PAWN
			case 0:
				return this.piecesImages.whitePawn;
				
			// KING
			case 1:
				return this.piecesImages.whiteKing;
				
			// QUEEN
			case 2:
				return this.piecesImages.whiteQueen;
				
			// KNIGHT
			case 3:
				return this.piecesImages.whiteKing;
				
			// ROOK
			case 4:
				return this.piecesImages.whiteRook;
				
			// BISHOP
			case 5:
				return this.piecesImages.whiteBishop;
				
			}
		}
		
		return null;
	}
}
