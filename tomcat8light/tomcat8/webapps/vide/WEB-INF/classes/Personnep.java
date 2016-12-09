public class Personnep
{
    public int num;
    public String login;
    public String mdp;
    public String nom;
    public String type;

    public Personnep(int num, String login, String mdp,String nom, String type)
    {
	maj(num,login,mdp,nom,type);
    }

    public void maj(int num, String login, String mdp,String nom, String type)
    {
	this.login = login;
	this.mdp = mdp;
	this.nom = nom;
    this.type = type;
    }

    public void maj2(String login, String mdp,String nom, String type)
    {
	this.login = login;
	this.mdp = mdp;
    this.nom = nom;
    }

    
}
