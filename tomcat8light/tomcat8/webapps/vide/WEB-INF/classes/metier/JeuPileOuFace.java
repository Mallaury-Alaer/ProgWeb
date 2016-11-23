package metier;

import java.util.* ;
public class JeuPileOuFace{
	private ArrayList<Character> humain;
	private ArrayList<Character> ordi;
	private int pointsHumain;
	private int pointsOrdi;
	private int numPartie;

	// réinitialise une partie
	public  void init(){
		humain =new ArrayList<Character>();
		ordi =new ArrayList<Character>();
		pointsHumain=0;
		pointsOrdi=0;
		numPartie=0;
	}

	public boolean termine(){
		return pointsHumain==10 || pointsOrdi==10;
	}

	public int getPointsHumain(){
		return pointsHumain;
	}

	public int getPointsOrdi(){
		return pointsOrdi;
	}

	public char getLastHumain(){
		return humain.get(humain.size()-1).charValue();
	}

	public char getLastOrdi(){
		return ordi.get(ordi.size()-1).charValue();
	}

	// joue, avec h comme coup du joueur humain
	public void play(char h){
		// l’IA : choisit o : le coup de l’ordi
		char o;
		if (Math.random()>0.5)
			o='P';
		else o='F';
		// range dans l’historique des coups
		humain.add(new Character(h));
		ordi.add(new Character(o));
		// et compte les points
		// deux faces identiques donne le point à l’ordi
		if (h==o) pointsOrdi++; else pointsHumain++;
		numPartie++;
	}
/*
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		init();
		while(!termine()){
			System.out.println("Pile ou Face :");
			String rep = sc.nextLine();
			
			if(rep.equals("p")){
				play('P');
			}else if(rep.equals("f")){
				play('F');
			}
			System.out.println("Ordi: "+pointsOrdi+" | Humain: "+pointsHumain);
		}
		if(pointsOrdi==10){
			System.out.println("L'ordinateur gagne ! ");
		}else if(pointsHumain == 10){
			System.out.println("Vous avez gagné ! ");
		}
		sc.close();
		
	}
*/
}