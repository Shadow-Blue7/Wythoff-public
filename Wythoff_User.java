/** 
 * Jeu de Wythoff partagé sur Github
 *@author : Un etudiant de BUT informatique
 */
 
class Wythoff_User{
	void principal(){
		int mode;
		int lg;
		char verif;
		int modifi;
		char[][] plateauBase;
		int[] position;
		char modif = ' ';
		boolean sim;
		String pseudo1 = "a";
		String pseudo2 = "b";
		
		fonctionTest();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		
		
		//On demande le mode en premier a l'utilisateur 
		System.out.println("========== BIENVENUE SUR LE JEU DE WYTHOFF ==========");
		mode=mode();
		System.out.println();
		
		
		//On demande ensuite les pseudos 
		System.out.println("Veuillez choisir votre/vos pseudos : ");
		if(mode==2){
			pseudo1=SimpleInput.getString("Pseudo joueur 1 : ");
			System.out.println();
			do{
				pseudo2=SimpleInput.getString("Pseudo joueur 2 : ");
				sim = pseudo1.equals(pseudo2); // retourne true ou false
				if(sim==true){
					System.out.println("Vous ne pouvez pas avoir le même pseudo !");
					System.out.println("Le joueur 2 doit sélectionner un autre pseudo...");
					System.out.println();
				}
			}while(sim==true);
			
		//Demande de pseudo pour l'utilisateur, qui ne peut pas sélectionner "bot" ou "Bot"
		}else if(mode==1){
			do{
				pseudo1=SimpleInput.getString("Votre pseudo : ");
				if(pseudo1.equals("bot")||pseudo1.equals("Bot")){
					System.out.println("Votre pseudo ne peut pas être identique au bot");
					System.out.println();
				}
			}while(pseudo1.equals("bot")||pseudo1.equals("Bot"));
		}
		//Eventuellement, on laisse la possibilite a l'utilisateur de modifier son pseudo 
		if(mode==2){
			System.out.println("Vos pseudos sont : "+pseudo1+" et "+pseudo2);
			System.out.println();
			do{
				modif=SimpleInput.getChar("Vos pseudos vous conviennent t'ils ? //Non=n; Oui=y// : ");
				if(modif!='n' && modif!='y' && modif!='N' && modif!='Y'){
					System.out.println("Veuillez sélectionner une option valide ! ");
					System.out.println();
				}
			}while(modif!='n' && modif!='y' && modif!='N' && modif!='Y');
		}else if(mode==1){
			System.out.println("Votre pseudo est : "+pseudo1);
			System.out.println();
			do{
				modif=SimpleInput.getChar("Votre pseudo vous convient t'il ? //Non=n; Oui=y// : ");
				if(modif!='n' && modif!='y' && modif!='N' && modif!='Y'){
					System.out.println("Veuillez sélectionner une option valide ! ");
					System.out.println();
				}
			}while(modif!='n' && modif!='y' && modif!='N' && modif!='Y');
		}
			//Conditions de sécurites dependant du cas utilisateur et du cas du mode select.
		if(modif=='N'||modif=='n' && mode==1){
			pseudo1=SimpleInput.getString("Votre nouveau pseudo : ");
			System.out.println();
		}else if(modif=='N'||modif=='n' && mode==2){
			do{
				modifi=SimpleInput.getInt("Modifier pseudo 1, 2 ou les deux ? (3) : ");
				System.out.println();
				if(modifi!=1 && modifi !=2 && modifi!=3){
					System.out.println("Entrez un pseudo existant à modifier");
					System.out.println();
				}
			}while(modifi!=1 && modifi !=2 && modifi!=3);
			
			//Ici on modifie le pseudo en fonction de la demande 
			if(modifi==1){
				pseudo1=SimpleInput.getString("Modification du pseudo 1 : ");
				System.out.println();
			}else if(modifi==2){
				pseudo2=SimpleInput.getString("Modification du pseudo 2 : ");
				System.out.println();
			}else if(modifi==3){
				pseudo1=SimpleInput.getString("Modification du pseudo 1 : ");
				System.out.println();
				pseudo2=SimpleInput.getString("Modification du pseudo 2 : ");
				System.out.println();
			}
		}
		//Confirmation utilisateur des pseudos
		System.out.println();
		System.out.println("Pseudos enregistrés :");
		System.out.println();
		if(mode==1){
			System.out.println(pseudo1);
		}else{
			System.out.println(pseudo1);
			System.out.println("/// VS ///");
			System.out.println(pseudo2);
			System.out.println();
		}
		
		
		//On creee ensuite le tableau via l'entree utilisateur 
		lg=saisirLongeurTab();
		plateauBase=creerTableaux(lg);
		
		//On place un pion aleatoirement sur le plateau 
		//On obtient egalement un tableau des coordonees du premier pion
		position=placementAleatoire(lg, plateauBase);
		
		//Si le mode solo est selectionne, on affiche nos position gagntante 
		if(mode==1){
			int[][] posGagne=AffichePositionGagnante(plateauBase);
		}
		//Communication avec l'utilisateur : On affiche notre plateau de base 
		System.out.println("Voici le plateau sur lequel vous jouerez et sa première position : ");
		afficheTab(plateauBase);
		if(mode==1){
			System.out.println("Puisque vous êtes en solo, les positions gagnantes sont représentés par un '.'");
			System.out.println();
		}
		
		if(mode==2){
			modDuo(plateauBase, position, pseudo1, pseudo2);
		}else if(mode==1){
			modSolo(plateauBase, position, pseudo1);
		}
		
		//Affichage final pour confirmation execution correct 
		System.out.println();
		System.out.println();
		System.out.println("MERCI D'AVOIR JOUÉ ! ");
		System.out.println("==================== FIN DU JEU WYTHOFF ====================");
		
	}
	
	/**
	 * Represente le mode solo de cette partie 
	 * @param plateau : Le tableau créer 
	 * @param position : La position aleatoire choisis 
	 * @param p1 : pseudo 1 
	 */
	void modSolo(char[][] plateau, int[] position, String p1){
		int tour=0;
		boolean commence;
		boolean fin=false;
		String p2="bot";
		
		commence=quiCommence();
		if(commence==true){
			tour=1;
		}else if(commence==false){
			tour=2;
		}
		
		while(fin==false){
			//Tour du joueur humain
			if(tour==1){
				if(fin==false){
					System.out.println("===============================================");
				}
				System.out.println();
				System.out.println();
				System.out.println("Au tour du joueur  " +p1);
				position(position);
				modifierPlateau(plateau, position);
				System.out.println();
				System.out.println("Voici l'état du tableau après le jeu du joeur "+p1);
				System.out.println();
				afficheTab(plateau);
				System.out.println();
				fin=estGagnant(position);
				if(fin==false){
					System.out.println("===============================================");
				}
				
			//TOur du joueur robot
			}else if(tour==2){
				if(fin==false){
					System.out.println("===============================================");
				}
				System.out.println();
				System.out.println();
				System.out.println("Au tour du bot ! ");
				System.out.println("Le bot réfléchit...");
				jeuBot(plateau, position);
				System.out.println();
				System.out.println("Voici le tableau après le jeu du bot : ");
				afficheTab(plateau);
				fin=estGagnant(position);
				if(fin==false){
					System.out.println("===============================================");
					System.out.println();
					System.out.println();
				}
			}
			//On change de joueurs
			if(fin==false){
				if(tour==1){
					tour=2;
				}else{
					tour=1;
				}
			}
		}
		System.out.println("FIN DU JEU !");
		System.out.println();
		if(tour==1){
			System.out.println("LE JOEUR "+p1+" A GAGNÉ ! Bravo ");
		}else{
			System.out.println("LE BOT A GAGNÉ ! Vous aurez une meilleure chance la prochaine fois...");
		}
		System.out.println();
	}
	
	/**
	 * Fonction qui represente les actions automatises du bot.
	 * Il doit analyser le plateau et voir si il peut rejoindre une positiion 
	 * gagnante. Si oui, il doit y aller durant son tour. Sinon, il doit jouer
	 * aleatoirement 
	 * @param plateau : Le plateau du jeu
	 * @param position : La position actuel du pion
	 */
	void jeuBot(char[][] plateau, int[] position){
		int x = position[0];
		int y = position[1];
		boolean posGagne = false;
		int action=0;
		
		
		//On cherche d'abord une position gagnante en x 
		while(posGagne==false && x!=0){
			x=x-1;
			if(plateau[y][x]=='.' || plateau[y][x]=='='){
				posGagne=true;
			}
		}
		
		//Puis on cherche en y si x n'a pas ete trouve
		while(posGagne==false && y!=0){
			x=position[0];
			y=y-1;
			if(plateau[y][x]=='.' || plateau[y][x]=='='){
				posGagne=true;
			}
		}
		
		//Enfin, on cherche en diagonale si x et y ne sont pas trouve 
		if(posGagne==false){
			x=position[0];
			y=position[1];
		}
		while(posGagne==false && y!=0 && x!=0){
			x=x-1;
			y=y-1;
			if(plateau[y][x]=='.' || plateau[y][x]=='='){
				posGagne=true;
			}
		}
		
		
		//Ici, si une position gagnante a ete trouvee, alors elle est en memoire via les var. x et y
		//On va lui faire jouer une position gagnante 
		if(posGagne==true){
			position[0]=x;
			position[1]=y;
			modifierPlateau(plateau, position);
			
			
			
		//Sinon, on joue aleatoirement 
		}else{
			
			//Ici, on va verifier que on peut se deplacer de tous les cotes, sinon il y a un risque de boucle infini ou sauter le tour
			boolean bas = true;
			boolean gauche = true;
			boolean diag = true;
			
			if(position[0]==0){
				gauche = false;
			}else if(position[1] == 0){
				bas = false;
			}
			
			//Si x ou y est déjà a son max, impossible d'aller en diagonale
			if(gauche == false || bas == false){
				diag=false;
			}
			
			
			//Aleatoire pour savoir ce qu'on joue (haut, bas, diagonale)
			//Repere : 1 pour a gauche, 2 pour en bas, 3 pour en diagonale
			int tempo=0;
			int tempo2=0;
			
			//Si la diagonale est possible, tous les cas sont permis, on laisse le hasard
			if(diag==true){
				tempo = (int) (Math.random() * 10);
				
			//Sinon on doit aider le programme a ne pas depasser
			}else if(gauche==true && bas == false){
				//on va forcément a gauche
				tempo = 3;
				tempo2 = 3;
				
			}else if(gauche==false && bas == true){
				//on va forcement en bas
				tempo = 3;
				tempo2 = 7;
			}
			
			if(tempo<5){
				//On choisit entre bas et gauche
				
				if(diag==true){
					tempo2= (int) (Math.random() * 10);
				}
				
				if(tempo2 <5){
					//On va à gauche
					action=1;
				}else{
					//On va en bas
					action=2;
				}
			}else{
				//On va en diagonale
				action=3;
			}
			
			//On remet nos variable x et y a leur origine 
				x=position[0];
				y=position[1];
			//Partie du jeu aleatoire avec son action dans la variable (1,2 ou 3)
			
			//Pour aller à gauche
			if(action==1){
				int jeu1=0;
				int max1 = position[0];
				while(jeu1<1 || jeu1>max1){
					jeu1 = (int) (Math.random() * 100);
				}
				position[0]=x-jeu1;
				modifierPlateau(plateau, position);
				
			//Pour descendre
			}else if(action==2){
				int jeu2=0;
				int max2 = position[1];
				while(jeu2<1 || jeu2>max2){
					jeu2= (int) (Math.random() * 100);
				}
				position[1]=y-jeu2;
				modifierPlateau(plateau, position);
			
			//Pour en diagonale
			}else if(action==3){
				int jeu3=0;
				int max3;
				//On vérifie quel limite entre x ou y est le plus petit 
				if(position[0]<position[1]){
					max3=position[0];
				}else{
					max3=position[1];
				}
				while(jeu3<1 || jeu3>max3){
					jeu3 = (int) (Math.random() * 100);
				}
				position[0]=x-jeu3;
				position[1]=y-jeu3;
				modifierPlateau(plateau, position);
			}
		}
	}
	
	/**
	 * Represente le mode Duo de cette partie
	 * @param plateau : Le tableau créer 
	 * @param position : La position aleatoire choisis 
	 * @param p1 : Le pseudo du joueur 1 
	 * @param p2 : Le pseudo du joueur 2 
	 */
	void modDuo(char[][] plateau, int[]position, String p1, String p2){
		int tour=0;
		String temp;
		boolean fin=false;
		
		System.out.println();
		do{
			temp=SimpleInput.getString("Qui veut commencer ? "+p1+"/"+p2+" : ");
			if(!temp.equals(p1) && !temp.equals(p2)){
				System.out.println("Veuillez entrer un pseudo valide ! ");
				System.out.println();
			}
		}while(!temp.equals(p1) && !temp.equals(p2));
		
		//Décide qui va commencer
		if(temp.equals(p1)){
			System.out.println("Le joueur "+p1+" va commencer !");
			tour = 1;
		}else if(temp.equals(p2)){
			System.out.println("Le joueur "+p2+" va commencer !");
			tour = 2;
		}
		
		//Boucle principal du jeu duo
		while(fin !=true){
			if(tour==1){
				System.out.println("Au tour du joueur  " +p1);
				position(position);
				modifierPlateau(plateau, position);
				System.out.println();
				System.out.println("Voici l'état du tableau après le jeu du joeur "+p1);
				System.out.println();
				afficheTab(plateau);
				System.out.println();
				fin=estGagnant(position);
				if(fin==false){
					System.out.println("===============================================");
					System.out.println();
					System.out.println();
				}
			}else if(tour==2){
				System.out.println("Au tour du joueur  " +p2);
				position(position);
				modifierPlateau(plateau, position);
				System.out.println();
				System.out.println("Voici l'état du tableau après le jeu du joeur "+p2);
				System.out.println();
				afficheTab(plateau);
				System.out.println();
				fin=estGagnant(position);
				if(fin==false){
					System.out.println("===============================================");
					System.out.println();
					System.out.println();
				}
			}
			if(fin!=true){
				if(tour==1){
					tour=2;
				}else if(tour==2){
					tour=1;
				}
			}
		}
		System.out.println("FIN DU JEU !");
		System.out.println();
		System.out.print("LE JOUEUR ");
		if(tour==1){
			System.out.print(p1);
		}else{
			System.out.print(p2);
		}
		System.out.print(" A GAGNÉ ! Bien joué ! ");
		System.out.println();
	}
		
	/**
	 * Fonction qui permet de selectionner si le mode doit etre duo ou solo
	 * @return Le numero attribue au mode de jeu
	 */
	int mode(){
		int mode;
		char choix;
		System.out.println();
		System.out.println("Veuillez choisir un mode pour le jeu : ");
		System.out.println();
		System.out.println();
		System.out.println("S pour solo et jouer face à une IA ");
		System.out.println("D pour duo et jouer face à un humain");
		System.out.println();
		System.out.println();
		
		do{
			choix = SimpleInput.getChar("Votre sélection : s/d  ");
			if(choix!='s'&&choix!='d'&&choix!='S'&&choix!='D'){
				System.out.println("La valeur " +choix+ " n'est pas correct");
			}
			
		}while(choix!='s'&&choix!='d'&&choix!='S'&&choix!='D');
		
		System.out.println();
		
		if(choix=='s' || choix=='S'){
			mode = 1;
			System.out.println("Choix du mode : SOLO");
		}else{
			mode = 2;
			System.out.println("Choix du mode : DUO");
		}
		
		return mode;
	}
		
	/** 
	* Fonction qui demande a l'utilisateur le nombre de cases avec lequel 
	* il veut jouer (longueur du plateau). Puisqu'il s'agit d'un carre, 
	* longeur = largeur
	* @return : La longeur demande par l'utilisateur du plateau 
	*/
	int saisirLongeurTab(){
		int lg;
		System.out.println("Vous allez maintenant choisir la taille du jeu !");
		System.out.println("Elle doit être comprise entre 4 et 16 inclus ");
		System.out.println();
		do{
			lg=SimpleInput.getInt("Quelle longueur pour le plateau ? : ");
			if(lg<4){
				System.out.println("La valeur "+lg+" n'est pas > ou = à 4");
				System.out.println("(C'est quoi ? Un morpion ? Un peu plus grand quand même ... )");
				System.out.println();
			}else if(lg>16){
				System.out.println("La valeur "+lg+" n'est pas < ou = à 16 !");
				System.out.println("(Le but n'est pas de jouer sur un tableau de 4km ;) )");
				System.out.println();
			}
		}while(lg<4 || lg>16);
		return lg;
	}
	
	/**
	 * Fonction qui creee un tableau de char en fonction du nombre 
	 * qu'a donne l'utilisateur dans lg 
	 * Il va egalement ajouter dans le tab la case d'arrivee 
	 * @param lg : QUel longeur de tableau
	 * @return plateau : Un tableau de char (Le plateau de jeu ) 
	 */
	char[][] creerTableaux(int lg){
		//Il s'agit d'un tableau carré, lg = longeur = largeur 
		char[][] plateau = new char [lg][lg];
		
		for(int i = 0; i<plateau.length; i++){
			for(int j = 0; j<plateau[i].length; j++){
				plateau[i][j]=' ';
			}
		}
		plateau[0][0]='=';
		return plateau;
	}
	
	/**
	 * Si le mode solo est selectionne, alors on affiche les positiosn gagnantes par 
	 * cette fonction (qui est donc appel uniquement en mode solo)
	 * @param plateau : Le plateau precedemment creer 
	 * @return posGagne: Le tableau des positions gagnantes pour le bot
	 */
	int[][] AffichePositionGagnante(char[][] plateau){
		int lg = plateau.length;
		int[][] posGagne=new int[3][lg];
		boolean [] utilise = new boolean[lg];
		int rang = 2;
		int x = 1;
		int y = 2;
		
		boolean pos=false;
		int i;
		int j;
		int rg=1;
		
		posGagne[0][0]=1;
		utilise[0]=true;
		//Et les 2 positions [1][0] et [2][0] sont donc à 0 automatiquement dans posGagne
		
		while(y<lg){
			posGagne[0][rang-1]=rang;
			posGagne[1][rang-1]=x;
			posGagne[2][rang-1]=y;
			
			utilise[x]=true;
			utilise[y]=true;
			rang ++;
			
			while(x<lg && utilise[x]){
				x++;
			}
			
			y=rang+x-1;
		}
		
		while(pos==false){
			i=posGagne[1][rg];
			j=posGagne[2][rg];
			
			plateau[i][j] = '.';
			plateau[j][i] = '.';
			rg++;
			if(posGagne[2][rg]!=0){
				i++;
				j++;
			}else{
				pos=true;
			}
		}
		return posGagne;
	}
		
		
		
		
		
	/**
	 * Fonction complete de selection d'une position aleatoire sur le plateau 
	 * Elle doit etre forcement sur un cote, x max ou y max, et ne peut 
	 * pas etre sur la position [0][0] ni sur la position maximum tel que
	 * pion = [max][max]
	 * Place le pion et renvoie le tableau pour utilisation externe
	 * @param max : longeur du tableau carré 
	 * 
	 * @return coord : Coordonee dans un tab du placement du pion 
	 */
	int [] placementAleatoire (int max, char [][] plateauBase){
		//On decremennte 1 pour eviter de depasser le tableau des maintenant
		max = max-1;
		int x =0;
		int y = 0;
		//Pour un aleatoire total, on va decider d'abord si on commence sur 
		//le cote y max ou x max
		int choix1 = (int) (Math.random() * 2);
		
		//Condition pour choisir x ou y aleatoirement 
		if(choix1==0){
			y = max;
			x = -1;
			while(x<1 || x>=max){
				x= (int) (Math.random() *100);
			}
		}else{
			x = max;
			y = -1;
			while(y<1 || y>=max){
				y= (int) (Math.random() * 100);
			}
		}
		
		//On place dans un tableau pour faciliter le return 
		int[] coord = new int [2];
		coord [0] = x;
		coord [1] = y;
		
		plateauBase[y][x]='O';
		return coord;
	}
	
	/**
	 * Fonction qui permet à l'utilisateur qui joue de déplacer le pion 
	 * dans l'espace du tableau selon 3 facons : 
	 * Deplacer vers la gauche 
	 * Deplacer vers la droie 
	 * Deplacer vers la diagonale 
	 * @param int[]position 
	 * @return position : nouvelle position du pion
	 */
	int[] position (int[] positionA){
		//Facon de décider : G pour gauche, B pour bas, D pour diagonale
		char choix;
		int g=0;
		int b=0;
		int d=0;
		//On récupère le tableau 
		int x=positionA[0];
		int y = positionA[1];
		
		//On demande a l'utilisateur quelle action 
		int limite=0;
		do{
			System.out.println("B=Bas; G=Gauche; D=Diagonale");
			choix = SimpleInput.getChar("Quel action sur le pion ?");
			System.out.println();
			limite = limite(choix, x, y);
	
			//Message d'erreur si commande inconnue ou impossible
			if(limite<1){
				System.out.println("Mouvement "+choix+" impossible ou inconnu, reessayez !");
				System.out.println();
			}
		}while(limite<1);
		
		//Message personalisee en fonction de l'action
		//Savoir de combien de cases décaler 
		if(choix=='g' || choix=='G'){
			do{
				g = SimpleInput.getInt("De combien vers la gauche ? ");
				if(g<1 || 	g>limite){
					System.out.println("IMPOSSIBLE : DEPASSEMENT DE TABLEAU");
					System.out.println("Valeur maximum : " +limite);
					System.out.println();
					}
			}while(g<1 ||g>limite);
			
		}else if(choix== 'b' || choix== 'B'){
			do{ 
				b = SimpleInput.getInt("De combien vers le bas ? ");
				if(b<1 || b>limite){
					System.out.println("IMPOSSIBLE : DEPASSEMENT DE TABLEAU");
					System.out.println("Valeur maximum : " +limite);
					System.out.println();
					}
			}while(b<1 || b>limite);
			
		}else if(choix== 'd' || choix== 'D'){
			do{
				d = SimpleInput.getInt("De combien en diagonale ? ");
				if(d<1 || d>limite){
					System.out.println("IMPOSSIBLE : DEPASSEMENT DE TABLEAU");
					System.out.println("Valeur maximum : " +limite);
					System.out.println();
					}
			}while(d<1 || d>limite);
		}
		
		
		
		//On place le pion à la nouvelle coordonnee demandee
		if(choix=='g' || choix=='G'){
			x=x-g;
		}else if(choix== 'b' || choix== 'B'){
			y=y-b;
		}else if(choix== 'd' || choix== 'D'){
			x=x-d;
			y=y-d;
		}
		positionA[0]=x;
		positionA[1]=y;
				
		return positionA;
	}
	
	/**
	 * Cette fonction est LIEE A LA FONCTION POSITION
	 * Fonction qui permet de savoir en x ou en y la position maximum
	 * pour le pion que l'utilisateur peur bouger
	 * @param char action : L'action qui est demandée 
	 * @param x : Abscysse du pion
	 * @param y : Ordonnée du pion
	 * @return nb : Le nombre de cases maximum avant de dépasser
	 */
	int limite(char action, int x, int y){
		//On stock la limite dans nb
		int nb=0;
		
		//En fonction de l'action demande
		if(action=='g' || action=='G'){
			nb = x;
		//Puisque c'est un carre, sa position est donc son maximum
		}else if(action=='b' || action=='B'){
			nb = y;
		//Le maximum en diagonale est simplement y ou x le plus petit 
		}else if(action=='d' || action=='D'){
			if(x>y){
				nb = y;
			}else if(x<y){
				nb = x;
			}else if(x==y){
				nb = x;
			}
		}
		return nb;
	}
			
	/**
	 * Fonction qui deplace le pion d'une position donne a une autre 
	 * @param char [][] jeu : Le plateau à modifier 
	 * @param int [] t2 : Nouvelle position 
	 * @return jeu : Le plateau de jeu modifié 
	 */
	char[][]modifierPlateau (char[][] jeu, int[] t2){
		int i = 0; 
		int j; 
		boolean trouv=false;
		while(i<jeu.length && trouv==false){
			j=0;
			//On cherche le symbole precedent pour le supprimer
			while(j<jeu[i].length){
				if(jeu[i][j]=='O'){
					jeu[i][j]=' ';
					trouv = true;
				}else if(jeu[i][j]=='0'){
					jeu[i][j]=' ';
					trouv = true;
				}
				j++;
			}
			i++;
		}
		
		//On place le nouveau
		int x;
		int y;
		x=t2[0];
		y=t2[1];
		jeu[y][x]='0';
		
		return jeu;
	}
		
	
	/**
	 * Fonction qui verifie si le pion est sur la position gagnante. Si 
	 * oui, retourne un booleen positif pour arreter la partie. 
	 * @param tab : le tableau de la position actuel du pion
	 * @return fin=true : Si le pion est dans la case gagnante
	 */
	boolean estGagnant (int [] tab){
		int x = tab[0];
		int y = tab[1];
		boolean fin = false;
		if(x == 0 && y == 0){
			fin = true;
		}
		return fin;
	}
	
	
	/**
	 * Demande si le joueur veut commencer en 1er 
	 * @return true si le joueur veut commencer, false sinon
	 */
	boolean quiCommence(){
		boolean commence=false;
		char temp;
		System.out.println("Vous avez sélectionné le mode Solo");
		System.out.println("Souhaitez vous commencer ? Oui=y / Non=n");
		do{
			temp=SimpleInput.getChar("y/n ? :  ");
			if(temp!='y' && temp!='Y' && temp!='n' && temp!='N'){
				System.out.println("Valeur "+temp+" inconnue, veuillez entrer une valeur valide ! ");
				System.out.println();
			}
		}while(temp!='y' && temp!='Y' && temp!='n' && temp!='N');
		
		if(temp=='y' || temp=='Y'){
			commence = true;
			System.out.println("Vous commencerez ! ");
			System.out.println();
		}else if(temp=='n' || temp == 'N'){
			commence = false;
			System.out.println("Le bot commencera !");
			System.out.println();
		}
		return commence;
	}
		
	/**
	 * Fonction qui affiche l'etat du tableau au moment ou on lui demande
	 * On affiche un tableau inverse pour facilité les lectures et pour 
	 * que le reste du prog soit plu s facile 
	 * @param tab : Le tableau 
	 */
	void afficheTab(char[][] tab){
		//La variable permettant d'avoir le numero de COLONNES 
		int numL=tab.length-1;
		
		for(int i=0;i<tab.length;i++){
			//Ici, c'est l'affichage des numeros à la suite
			if(tab.length > 10 && numL < 10){
				System.out.print(" ");
			}
			System.out.print(numL);
			System.out.print(" | ");
			
			//Puis on affiche le contenu du tableau à l'envers 
			for(int j = 0; j<tab[numL].length; j++){
				System.out.print(tab[numL][j]);
				System.out.print(" | ");
				
			}
			
			//on retourne à la ligne et on decremente numL. De cette 
			//façon, on affiche le tableau "a l'envers" = facile lire
			System.out.println();
			numL=numL-1;
		}
		
		//AFFICHAGE DES NUM DE COLONNES 
		//On centre pour eviter que tous soit decale 
		if(tab.length > 10){
			System.out.print("     ");
		}else{
			System.out.print("    ");
		}
		//Affichage des nb de colonnes  
		for(int j = 0; j < tab.length; j++){
			System.out.print(j);
			//Condition pour que le 10 n'empiete pas 
			//sur la colonne suivante 
			if(j < 10){
				System.out.print("   ");
			}else{
				System.out.print("  ");
			}
		}
		System.out.println();
	}
	
	/**
	 * Premiere fonction de verification qui stock les tests et les résultats attendues
	 * 
	 */
	void fonctionTest(){
		System.out.println("Vérification de sécurité (à ignorer) : ");
		estGrand(4,4);
		estGrand(13, 13);
		estGrand(16,16);
		
		System.out.println();
		afficheArriv(4);
		afficheArriv(8);
		afficheArriv(16);
		
		System.out.println();
		System.out.println("Fonction test pour savoir si plusieurs positions sont gagnantes");
		int[]tab1 = {0,0};
		int[]tab2 = {0,5};
		int[]tab3 = {9,0};
		boolean ex1=true;
		boolean ex2=false;
		estPositionGagnante(tab1, ex1);
		estPositionGagnante(tab2, ex2);
		estPositionGagnante(tab3, ex2);
		
		
	}
	
	/**
	 * Fonction d'appel test qui test si un tableau genere correspond bien a la longeur transmises 
	 * @param lg : La longeur du tableau 
	 * @param tp : La longeur theorique
	 */
	void estGrand(int lg, int tp){
		int temp;
		char[][]test = creerTableaux(lg);
		temp = test.length;
		System.out.print("Fonction test d'un tableau pour lg = "+lg+" :   ");
		if(temp==tp){
			System.out.print("OK");
		}else{
			System.out.print("ERREUR !");
		}
		System.out.println();
	}
	
	/**
	 * Vérifie qu'un tableau possède bien une case gagnante modelise par 
	 * le symbole =
	 */
	void afficheArriv(int lg){
		char[][] test2 = creerTableaux(lg);
		placementAleatoire(lg, test2);
		System.out.print("Fonction test si ''='' est bien placé pour un tableau de "+lg+" de longeur : ");
		if(test2[0][0]=='='){
			System.out.print("OK");
		}else{
			System.out.print("ERREUR ! ");
		}
		System.out.println();
	}
	
	/**
	 * Vérifie qu'une position x=0 et y=0 declenche une condition gagnante 
	 * et que plusieurs autres cas ne l'a déclenche pas 
	 * @param tab : Le tableau theorique d'une position a un moment t
	 * @param ex : La position donne doit etre gagnante ?
	 */
	void estPositionGagnante(int[] tab, boolean ex){
		boolean test; 
		int x = tab[0];
		int y = tab[1];
		
		test=estGagnant(tab);
		System.out.print("La position x=["+x+"] y=["+y+"]");
		if(ex==true){
			System.out.print("est gagnante :   ");
		}else if(ex==false){
			System.out.print("n'est pas gagnante :   ");
		}
		
		if(test==ex){
			System.out.print("OK");
		}else{
			System.out.print("ERREUR !");
		}
		System.out.println();
	}
}
