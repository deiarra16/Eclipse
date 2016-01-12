package mundo;

import java.awt.image.BufferedImage;
import java.util.Random;

public class Laberinto
{
	private int FILAS;
	private int COLUMNAS;
	int numeroGalletas=0;
	int megaPuntos=0;
	Personaje [][] matriz;
	int nivel=1;
	Random random;

	public Laberinto(int filas,int columnas)
	{
		FILAS=filas;
		COLUMNAS=columnas;
		matriz = new Personaje [FILAS][COLUMNAS];
		random = new Random();
	}

	public void setFilas(int filas)
	{
		FILAS=filas;
	}
	public int getFilas()
	{
		return FILAS;
	}
	public void setColumnas(int columnas)
	{
		COLUMNAS=columnas;
	}
	public int getColumnas()
	{
		return COLUMNAS;
	}
	public void generarLaberinto()
	{
		//coloca todas las imagenes del laberinto en null
		for (int i = 0; i < FILAS; i++)
		{
			for (int j = 0; j < COLUMNAS; j++)
			{
				matriz[i][j]=null;
			}
		}

		//genera el cuadrado del muro exterior
		for(int i =0;i<FILAS;i++)
		{
			for(int j=0; j<COLUMNAS;j++)
			{
				//llena con muro la primera y la ultima fila
				if(i==0||i==(FILAS-1))
				{
					matriz[i][j]=new Muro();

				}
				else
				{
					//llena con muro la primera y ultima columna
					if(j==0||j==(COLUMNAS-1))
					{
						matriz[i][j]= new Muro();
					}
				}

				//llena con galletas el cuadrado interior al muro
				if((i==1&&(j>0&&j<COLUMNAS-2))||i==FILAS-2&&(j>0&&j<COLUMNAS-2))
				{
					matriz[i][j]=new Galleta();
					numeroGalletas++;

				}
				else
				{
					if(j==1&&(i>0&&i<FILAS-2)||j==COLUMNAS-2&&(i>0&&i<FILAS-2))
					{
						matriz[i][j]=new Galleta();
						numeroGalletas++;
					}
				}

			}

		}

		for(int i=0;i<200;i++)
		{
			//genera coordenadas aleatorias para poner muros
			Double r= random.nextDouble()*(FILAS-2);
			int x = r.intValue();
			r = random.nextDouble()*(COLUMNAS-2);
			int y = r.intValue();
			/*para saber en que lugar coloca el muro y dos
			posiciones adyacentes para colocar galletas*/
			int condicion=0,tempX[]=new int[3],tempY[]=new int[3];

			if(matriz[x][y+1]==null&&condicion<3)
			{
				tempX[condicion]=x;
				tempY[condicion]=y+1;
				condicion++;
			}
			if(matriz[x+2][y+1]==null&&condicion<3)
			{
				tempX[condicion]=x+2;
				tempY[condicion]=y+1;
				condicion++;
			}
			if(matriz[x+1][y]==null&&condicion<3)
			{
				tempX[condicion]=x+1;
				tempY[condicion]=y;
				condicion++;
			}
			if(matriz[x+1][y+2]==null&&condicion<3)
			{
				tempX[condicion]=x+1;
				tempY[condicion]=y+2;
				condicion++;
			}
			if(condicion==3)
			{
				matriz[x+1][y+1]=new Muro();
				matriz[tempX[0]][tempY[0]]=new Galleta();
				numeroGalletas++;
				matriz[tempX[1]][tempY[1]]=new Galleta();
				numeroGalletas++;

			}

		}

		/*
		 * llena de galletas los espacios libres de la matriz
		 */
		for(int i=1;i<FILAS-1;i++)
		{
			for(int j=1;j<COLUMNAS-1;j++)
			{
				if(matriz[i][j]==null)
				{
					numeroGalletas++;
					matriz[i][j]=new Galleta();
				}
			}
		}
		
		//coloca los megaPuntos
		
		while(megaPuntos<4)
		{
			boolean megapuntoColocado=false;
			Double r = random.nextDouble()*3;
			int x = r.intValue();
			r = random.nextDouble()*3;
			int y = r.intValue();
			while(megapuntoColocado==false)
			{
				if(matriz[x+1][y+1].getNombre().equalsIgnoreCase("galleta"))
				{
					if(megaPuntos==0)
					{
						matriz[x+1][y+1]=null;
						matriz[x+1][y+1]=new MegaPunto();
						megaPuntos++;
						megapuntoColocado=true;
					}
					
				}
				if(matriz[x+(COLUMNAS-5)][y+1].getNombre().equalsIgnoreCase("galleta"))
				{
					if(megaPuntos==1)
					{
						matriz[x+(COLUMNAS-5)][y+1]=null;
						matriz[x+(COLUMNAS-5)][y+1]=new MegaPunto();
						megaPuntos++;
						megapuntoColocado=true;
					}
				}
				if(matriz[x+1][y+(FILAS-5)].getNombre().equalsIgnoreCase("galleta"))
				{
					if(megaPuntos==2)
					{
						matriz[x+1][y+(FILAS-5)]=null;
						matriz[x+1][y+(FILAS-5)]=new MegaPunto();
						megaPuntos++;
						megapuntoColocado=true;
					}
				}
				if(matriz[x+(COLUMNAS-5)][y+(FILAS-5)].getNombre().equalsIgnoreCase("galleta"))
				{
					if(megaPuntos==3)
					{
						matriz[x+(COLUMNAS-5)][y+(FILAS-5)]=null;
						matriz[x+(COLUMNAS-5)][y+FILAS-5]=new MegaPunto();
						megaPuntos++;
						megapuntoColocado=true;
					}
				}
				
				if(x<3)
				{
					x++;
				}
				else if(y<3)
				{
					y++;
				}
				else
				{
					x=0;
					y=0;
				}
				
			}
			megapuntoColocado=false;
			
		}
		
		megaPuntos=0;
	}

	public boolean buscarGalletas()
	{
		boolean hayGalletas=false;
		for (int i = 0; i < FILAS&&hayGalletas==false; i++)
		{
			for (int j = 0; j < COLUMNAS&&hayGalletas==false; j++)
			{
				if(matriz[i][j]!=null)
				{
					if(matriz[i][j].getNombre().equalsIgnoreCase("galleta")||matriz[i][j].getNombre().equalsIgnoreCase("megapunto"))
					{
						hayGalletas=true;
					}
				}
			}
		}
		return hayGalletas;
	}
	public int getNumeroGalletas()
	{
		return numeroGalletas;
	}
	public void removerGalleta(int x, int y)
	{
		numeroGalletas--;
		matriz[x][y]=null;
	}

	public Personaje [][] getMatriz()
	{
		return matriz;
	}

}
