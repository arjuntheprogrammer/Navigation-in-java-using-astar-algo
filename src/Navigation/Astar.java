package Navigation;

import java.util.Arrays;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Vector;

class Heur{
	Double f,g,h;
	int id; 
	//Heur parent;
}


public class Astar extends Object{

	double shortest_dist(Vector<Node> nodes, int[] parent, int src, int dest){
		Arrays.fill(parent, -1);
		Dist d=new Dist();
		double dist[] =new double[nodes.size()+10];
		Vector <Heur> open=new Vector<Heur>(250, 10);
		Vector <Heur> close = new Vector<Heur>(250, 10);
		//Dictionary parent = new Hashtable();
		System.out.println("here");
		
		Heur H=new Heur();
		H.id=src;
		H.f=0.0;
		H.g=0.0;
		H.h=0.0;
		//H.parent=null;
		
		
		open.addElement(H);
		
		int u, mini=0, found=0;
		while(open.size()!=0 && found!=1){
			
			
			mini=minIndex(open);
			u=open.elementAt(mini).id;
			
			Heur temp=new Heur();
			temp=open.elementAt(mini);			
			//open.remove(mini);
			System.out.println("here1 : "+temp.id);
			for(int i=0;i<nodes.elementAt(u).adj.size();i++){
				
				 //parent.put(nodes.elementAt(u).adj.elementAt(i),u);
				 
				
				
				Heur child=new Heur();
				
				//int x =BinarySearch.BSearch(nodes,0, nodes.size(),nodes.elementAt(u).adj.elementAt(i));
				
				child.id=nodes.elementAt(u).adj.elementAt(i);
				child.g=temp.g + nodes.elementAt(u).weight.elementAt(i);
				child.h=d.calculateDistance(nodes.elementAt(child.id).lat,  nodes.elementAt(child.id).lon,nodes.elementAt(dest).lat,  nodes.elementAt(dest).lon );
				child.f=child.h+child.g;
				//child.parent=open.elementAt(mini);
				//parent[child.id]=u;
				
				if(child.id==dest){
					 
					found=1;
					System.out.println("before inner  return ");
					parent[child.id]=u;
					 return child.f;
				}
				
				int f=0;
				for(int j=0; j<open.size();j++){
					if(open.elementAt(j).id==child.id){
						if(child.f<open.elementAt(j).f){
							open.setElementAt(child, j);
							
							parent[child.id]=u;
							f=1;
							break;
							
						}
						else{
							f=1;
							
						}
						break;
					}
					
				}
				
				if(f==0)
				for(int j=0; j<close.size();j++){
					if( close.elementAt(j).id==child.id){ 
						if (child.f<close.elementAt(j).f){
						
							open.addElement(child);
							
							close.removeElementAt(j);
							f=1;
							parent[child.id]=u;
						}
						else{
							f=1;
							
						}
						break;
					}
				}
				
				
				if(f==0){
					parent[child.id]=u;
					open.addElement(child);
				}
				

			
			}
			close.addElement(temp);
			open.remove(mini);
			
		}
			
		
		
		System.out.println("before return ");
		
		return -1.0;
		
	}
	
	int minIndex(Vector<Heur> open) {
		double min=Double.MAX_VALUE;
		int index=0;
		for(int i = 0; i <open.size(); i++){
			if(open.elementAt(i).f<min){
				min=open.elementAt(i).f;
				index=i;
			}
		}
		
		return index;
	}

}
