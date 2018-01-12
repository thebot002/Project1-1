package knapsack.greedy;

import java.util.ArrayList;

import javafx.geometry.Point3D;
import knapsack.components.Parcel;
import knapsack.components.Truck;

public class Greedy {
	public static void main(String[] args) {
		Truck truck = new Truck();
		Parcel parcelA = new Parcel("A");
		Parcel parcelB = new Parcel("B");
		Parcel parcelC = new Parcel("C");
		ArrayList<Parcel> parcelList = new ArrayList<Parcel>();
		Parcel[] parcelAr = new Parcel[10];
		
		Parcel xRotParcelA = new Parcel("A");
		xRotParcelA.xRotate();
		Parcel yRotParcelA = new Parcel("A");
		yRotParcelA.yRotate();
		Parcel zRotParcelA = new Parcel("A");
		zRotParcelA.zRotate();
		Parcel xyRotParcelA = new Parcel("A");
		xyRotParcelA.xRotate();
		xyRotParcelA.yRotate();
		Parcel xzRotParcelA = new Parcel("A");
		xzRotParcelA.xRotate();
		xzRotParcelA.zRotate();	
		Parcel yRotParcelB = new Parcel("B");
		yRotParcelB.yRotate();
		Parcel zRotParcelB = new Parcel("B");
		zRotParcelB.zRotate();
		
		parcelAr[0]=parcelA;
		parcelAr[1]=xRotParcelA;
		parcelAr[2]=yRotParcelA;
		parcelAr[3]=zRotParcelA;
		parcelAr[4]=xyRotParcelA;
		parcelAr[5]=xzRotParcelA;
		parcelAr[6]=parcelB;
		parcelAr[7]=yRotParcelB;
		parcelAr[8]=zRotParcelB;
		parcelAr[9]=parcelC;
		
		backTracking(truck, parcelAr, parcelList,  0);
	}
	
	public static boolean backTracking(Truck truck, Parcel[] parcelAr, ArrayList<Parcel> parcelList, int index) {
		
		int totVol = 0;
		int[] position = new int[3];
		
		truck.printTruck();
		
		for(int i=0; i<parcelList.size(); i++) {
			totVol+=parcelList.get(i).getVolume();
		}
		
		
		if(totVol>truck.getVolume()) {
			
//			System.out.println("HERE!!");
			return false;
			
		}else if((int)(0.90*truck.getVolume())<totVol && totVol<=truck.getVolume()){
			
			truck.printTruck();
//			System.out.println("MAYBE HERE???");
			return true;
			
		}else {
			
			position=truck.positionToAdd();
			Point3D p = new Point3D(position[0],position[1],position[2]);
			
			if(truck.isPossible(parcelAr[index], p	)) {
				
				Truck newTruck = new Truck();
				newTruck.setTruck(truck.copyTruck());
				newTruck.addParcel(parcelAr[index], p);
				parcelList.add(parcelAr[index]);
				return backTracking(newTruck, parcelAr, parcelList, index);
					
				
			}else {
				
					index++;
					if(index==9)
						index=0;
					
					return backTracking(truck, parcelAr,parcelList, index);
					
					
			}
			
			
				
				
			
		}
	}
}

