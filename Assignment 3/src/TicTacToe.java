import java.util.Scanner;

public class TicTacToe 
{
	static int xWins = 0, oWins = 0, totalGames = 0, turnCounter = 0;
	static char[][] gameBoard = new char[3][3];
	static char winner;
	
	public static void tttStart()														//Method which initiates the TicTacToe game
	{
		turnCounter = 0;

		for(int row=0; row<3;row++)														//Initialization of the board
		{
			for(int col=0; col<3;col++)
			{
				gameBoard[row][col] = Character.forDigit((1+col+row*3), 10);
			}
		}

		try(Scanner input = new Scanner(System.in))
		{
			while(true)																	//Displays the board on console
			{
				char player = (turnCounter%2==0)?'X':'O';								//Chooses appropriate player according to even or odd turn
				for(int row=0; row<3;row++)
				{
					for(int col=0; col<3;col++)
					{
						System.out.print(gameBoard[row][col]+" ");
					}
					System.out.print("\n");
				}

				if(!check()) break;														//Checks if there is a winner

				System.out.println("Player "+player+", please enter your position:");	//Prompts user for position
				while(true)
				{
					String posInput = input.nextLine();

					if(posInput.length()==1)
					{
						char digit = posInput.charAt(0);
						if (digit >= '1' && digit <= '9') 								//Checks validity of entry
						{																//Assigns entry to board
							int position = Character.getNumericValue(digit);
							if(assign(player, position)) break;
						}
					}
					System.out.println("Please enter a VALID position (1-9 and unplayed)!");
				}
				++turnCounter;
			}
			conclusion(input);
		}
	}

	static boolean assign(char player, int position)									//Method which writes position to board
	{
		int row=0;
		while((position-3)>0)
		{
			++row;
			position-=3;
		}
		if(gameBoard[row][position-1]<='9')												//Checks if position already taken
		{
			gameBoard[row][position-1] = player;
			return true;
		}else return false;
	}

	static boolean check()																//Method that verifies if there is a winner
	{
		boolean notWin = true;
		char[] posH = new char[3], posV = new char[3], posD1 = new char[3], posD2 = new char[3];
		int tieCheck=0;
		int revDiagCounter=2;

		for(int i=0;i<3;i++,revDiagCounter--)
		{
			for(int j=0;j<3;j++)
			{
				posH[j] = gameBoard[i][j];															//posH = horizontal win
				posV[j] = gameBoard[j][i];															//posV = vertical win
				if(posH[j]>'9') ++tieCheck;															//Checks if position has been played
			}
			posD1[i] = gameBoard[i][i];																//posD1 = diagonal win
			posD2[i] = gameBoard[i][revDiagCounter];												//posD2 = reverse diagonal win
			if(posH[0]==posH[1]&&posH[1]==posH[2]) {notWin = false; winner=posH[0]; break;}			//Verifies if there is a win on all combination
			if(posV[0]==posV[1]&&posV[1]==posV[2]) {notWin = false; winner=posV[0]; break;}		
			if(posD1[0]==posD1[1]&&posD1[1]==posD1[2]) {notWin = false; winner=posD1[0]; break;}
			if(posD2[0]==posD2[1]&&posD2[1]==posD2[2]) {notWin = false; winner=posD2[0]; break;}
			if(tieCheck==9) {notWin = false; winner='T'; break;}									//If there is a tie, the game ends
		}
		if(notWin==true) if(turnCounter>0) System.out.println("No winner yet..."); else System.out.println("**Game start!**");
		return notWin;
	}

	static void conclusion(Scanner input)								//Method which shows results and asks if user wants to replay
	{
		++totalGames;
		if(winner=='T') System.out.println("This game is a tie.");		//In case of a tie
		else 
		{
			System.out.println("---> Congratulation Player "+winner+"! You win this game! <---");
			if(winner=='X') ++xWins; else ++oWins;
		}

		System.out.println("Numbers of games played till now: "+totalGames);
		System.out.println("Player X has won "+xWins+((xWins>1)?" games":" game")+" and Player O has won "+oWins+((oWins>1)?" games.":" game."));
		System.out.println("Would you like to play again? Enter yes to replay or anything else to quit to main menu:");
		String option = input.nextLine();
		option = option.toLowerCase();
		option = option.trim();
		if(option.equals("yes")) tttStart();
		else
		{
			System.out.println("Total number of games played: "+totalGames);
			System.out.println("Player X has won "+xWins+" ("+(xWins*100/totalGames)+"%) "+((xWins>1)?"games":"game")+" and Player O has won "+oWins+" ("+(oWins*100/totalGames)+"%) "+((oWins>1)?"games.":"game."));
			if(xWins==oWins) System.out.println("---No champion, it was a tie!---");
			else System.out.println("***The grand chamption of Tic-Tac-Toe is "+((xWins>oWins)?"Player X!":"Player O!")+"***");
			System.out.println("----------------------------------------------------------------------------\n");
			xWins = oWins = totalGames = 0;
			Main.main(null);
		}
	}
}
