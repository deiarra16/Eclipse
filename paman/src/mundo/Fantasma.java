package mundo;

import java.awt.Rectangle;
import java.util.Random;
import java.util.StringTokenizer;

public class Fantasma extends Personaje implements Runnable 
{
	int posXPacman;
	int posYPacman;
	Thread hilo;
	public Fantasma(Laberinto laberinto,int posX, int posY)
	{
		hilo = new Thread(this,"hiloFantasma");
		miLaberinto=laberinto;
		nombre="fantasma";
		juegoTerminado=false;
		gameOver = false;
		this.posX=posX;
		this.posY=posY;
		imagen = cargarImagen("recursos/fantasma.GIF");
	}
	
	/*
	 * metodo para verificar si el fantasma se encuentra con pacman, 
	 * si el retorno es cero no hay colision
	 * si es uno hay colision con fantasma azul
	 * si es dos hay colision con fantasma rojo
	 */
	public int gameOver()
	{
		Rectangle r1 = new Rectangle(), r2=new Rectangle();
		r1.setBounds(posX, posY, 33, 33);
		r2.setBounds(posXPacman, posYPacman, 33, 33);
		if(r1.intersects(r2))
		{
			if(nombre.equalsIgnoreCase("fantasmaR"))
			{
				return 2;
			}
			else
			{
				return 1;
			}
			
		}
		return 0;
	}
	public void run() 
	{
		boolean movimiento=true;
		while(true)
		{
			if(gameOver==false)
			{
				if(movimiento==false)
				{	int random=0;
					Random rand = new Random();
					
					for(int j=0;j<10;j++)
					{
						Double r = rand.nextDouble()*3;
						random = r.intValue();
						switch (random)
						{
							case 0:
							{
								for(int i=0;i<11;i++)
								{
									if(evaluarDerecha()==true)
									{
										try {
											hilo.sleep(100);
										} catch (InterruptedException e) {
											
											e.printStackTrace();
										}
										posX+=5;
									}
								}
								break;
							}
							
							case 1:
							{
								for(int i=0;i<11;i++)
								{
									if(evaluarAbajo()==true)
									{
										try {
											hilo.sleep(100);
										} catch (InterruptedException e) {
										
											e.printStackTrace();
										}
										posY+=5;
									}
								}
								break;
							}
							
							case 2:
							{
								for(int i=0;i<11;i++)
								{
									if(evaluarIzquierda()==true)
									{
										try {
											hilo.sleep(100);
										} catch (InterruptedException e) {
										
											e.printStackTrace();
										}
										posX-=5;
										
										
									}
								}
								break;
							}
							case 3:
							{
								for(int i=0;i<11;i++)
								{
									if(evaluarAriba()==true)
									{
										try {
											hilo.sleep(100);
										} catch (InterruptedException e) {
										
											e.printStackTrace();
										}
										posY-=5;
									}
								}
								break;
							}
						}
					}
					
					
					
				}
				movimiento=false;
				if(posX<=posXPacman&&posY<posYPacman)
				{
					while((posX<=posXPacman&&evaluarDerecha()==true))
					{
						
						try {
							hilo.sleep(100);
						} catch (InterruptedException e) {
							
							e.printStackTrace();
						}
						posX+=5;
						movimiento=true;
					
					}
					while(posY<=posYPacman&&evaluarAbajo()==true)
					{
						try {
							hilo.sleep(100);
						} catch (InterruptedException e) {
							
							e.printStackTrace();
						}
						posY+=5;
						movimiento=true;
					}
				}
				
				if(posX<posXPacman&&posY>=posYPacman)
				{
					while((posX<=posXPacman&&evaluarDerecha()==true))
					{
						
						try {
							hilo.sleep(100);
						} catch (InterruptedException e) {
							
							e.printStackTrace();
						}
						posX+=5;
						movimiento=true;
					
					}
					while(posY>=posYPacman&&evaluarAriba()==true)
					{
						try {
							hilo.sleep(100);
						} catch (InterruptedException e) {
							
							e.printStackTrace();
						}
						posY-=5;
						movimiento=true;
					}
				}
				
				if(posX>posXPacman&&posY<=posYPacman)
				{
					while((posX>=posXPacman&&evaluarIzquierda()==true))
					{
						
						try {
							hilo.sleep(100);
						} catch (InterruptedException e) {
							
							e.printStackTrace();
						}
						posX-=5;
						movimiento=true;
					
					}
					while(posY<=posYPacman&&evaluarAbajo()==true)
					{
						try {
							hilo.sleep(100);
						} catch (InterruptedException e) {
							
							e.printStackTrace();
						}
						posY+=5;
						movimiento=true;
					}
				}
				
				if(posX>=posXPacman&&posY>posYPacman)
				{
					while((posX>=posXPacman&&evaluarIzquierda()==true))
					{
						
						try {
							hilo.sleep(100);
						} catch (InterruptedException e) {
							
							e.printStackTrace();
						}
						posX-=5;
						movimiento=true;
					
					}
					while(posY>=posYPacman&&evaluarAriba()==true)
					{
						try {
							hilo.sleep(100);
						} catch (InterruptedException e) {
							
							e.printStackTrace();
						}
						posY-=5;
						movimiento=true;
					}
				}
			}
			
			
				
						
		}
	
	}
	
	public Thread getHilo()
	{
		return hilo;
	}
	public boolean evaluarDerecha()
	{
		boolean existe = false;
		StringTokenizer tokens = new StringTokenizer(verificarColision());
		while(tokens.hasMoreTokens())
		{
			if(tokens.nextToken().equalsIgnoreCase("derecha"))
			{
				existe = true;
			}
		}
		if(existe==false)
		{
			return true;
		}
		
		return false;
	}
	
	
	
	public boolean evaluarAbajo()
	{
		boolean existe = false;
		StringTokenizer tokens = new StringTokenizer(verificarColision());
		while(tokens.hasMoreTokens())
		{
			if(tokens.nextToken().equalsIgnoreCase("abajo"))
			{
				existe = true;
			}
		}
		if(existe==false)
		{
			return true;
		}
		
		return false;
	}
	public boolean evaluarIzquierda()
	{
		boolean existe = false;
		StringTokenizer tokens = new StringTokenizer(verificarColision());
		while(tokens.hasMoreTokens())
		{
			if(tokens.nextToken().equalsIgnoreCase("izquierda"))
			{
				existe = true;
			}
		}
		if(existe==false)
		{
			return true;
		}
		
		return false;
	}
	public boolean evaluarAriba()
	{
		boolean existe = false;
		StringTokenizer tokens = new StringTokenizer(verificarColision());
		while(tokens.hasMoreTokens())
		{
			if(tokens.nextToken().equalsIgnoreCase("arriba"))
			{
				existe = true;
			}
		}
		if(existe==false)
		{
			return true;
		}
		
		return false;
	}
	
	public void setPosPacman(int x, int y)
	{
		posXPacman=x;
		posYPacman=y;
	}

}
