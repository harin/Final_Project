package sample;

import java.util.LinkedList;

import worldview.FetchInformation;
import worldview.NullIcetizen;

public class TestLink {
static int  testcase=0;

		public static void main(String[] args){
			FetchInformation fetcher = new FetchInformation();
			LinkedList<NullIcetizen> icetizen =  fetcher.getCitizen();
			
			
			LinkedList b = new LinkedList();
		
			for(int i=0;i<10;i++){
				b.add(i);
			}
//			while(!b.isEmpty()){
//			System.out.println("================");
//			System.out.println("size:"+b.size());
//			System.out.println("b information:"+b.pop());
//			System.out.println("================");
//			}
			
			while(!b.isEmpty()){
				System.out.println("The case#:"+testcase++);
				System.out.println(icetizen.pop());
			} 
			
		}
}
