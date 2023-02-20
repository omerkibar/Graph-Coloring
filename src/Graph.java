import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Graph {
    ArrayList<Vertex> vertices = new ArrayList<Vertex>();
    
    public Graph(String inputFileName) throws Exception {
        Scanner inputFile = new Scanner(new File(inputFileName));
        String[] line = inputFile.nextLine().split(" ");
        int numberOfVertices = Integer.parseInt(line[1]);
        for (int i = 0; i < numberOfVertices; i++) {
            vertices.add(new Vertex(i));
        }
        while (inputFile.hasNext()) {
            line = inputFile.nextLine().split(" ");
            int v1 = Integer.parseInt(line[1])-1;
            int v2 = Integer.parseInt(line[2])-1;
            addEdge(v1, v2);
        }
    }

    public void addEdge(int v1,int v2){
        vertices.get(v1).addAdjacency(vertices.get(v2));
        vertices.get(v2).addAdjacency(vertices.get(v1));
    }

    public int colorGraphBySorting(int[] colors){
        int numberOfVertices = vertices.size();
        Arrays.fill(colors,-1);
        Collections.sort(vertices,Comparator.reverseOrder());
        int currentColor = 0;
        for (int i = 0; i < numberOfVertices; i++) {
            Vertex currentVertex = vertices.get(i);
            if(colors[currentVertex.vertexId]!=-1) continue;
            colors[currentVertex.vertexId] = currentColor;
            for (int j = i+1; j < numberOfVertices; j++) {
                Vertex jthVertex = vertices.get(j);
                if(!currentVertex.adjacencies.contains(jthVertex) && colors[jthVertex.vertexId]==-1){
                    boolean isColorable = true;
                    for (int k = 0; k < jthVertex.adjacencies.size(); k++) {
                        if(colors[jthVertex.adjacencies.get(k).vertexId]==currentColor) isColorable = false; 
                    }
                    if(isColorable) colors[jthVertex.vertexId] = currentColor;
                }
            }
            currentColor++;
        }
        return currentColor;
    }

    public int colorGraph(int[] colors,int upperBound){
        int numberOfVertices = vertices.size();
        Arrays.fill(colors,-1);
        Collections.shuffle(vertices);
        int currentColor = 0;
        for (int i = 0; i < numberOfVertices; i++) {
            Vertex currentVertex = vertices.get(i);
            if(colors[currentVertex.vertexId]!=-1) continue;
            colors[currentVertex.vertexId] = currentColor;
            for (int j = i+1; j < numberOfVertices; j++) {
                Vertex jthVertex = vertices.get(j);
                if(!currentVertex.adjacencies.contains(jthVertex) && colors[jthVertex.vertexId]==-1){
                    boolean isColorable = true;
                    for (int k = 0; k < jthVertex.adjacencies.size(); k++) {
                        if(colors[jthVertex.adjacencies.get(k).vertexId]==currentColor) isColorable = false; 
                    }
                    if(isColorable) colors[jthVertex.vertexId] = currentColor;
                }
            }
            currentColor++;
            if(currentColor>=upperBound) return -1;//Exceeds upper bound
        }
        return currentColor;
    }
    public boolean validateColoring(int numberOfColors,int[] colors){
        for (int i = 0; i < this.vertices.size(); i++) {
            Vertex v = this.vertices.get(i);
            for (int j = 0; j < v.adjacencies.size(); j++) {
                if(colors[v.vertexId] == colors[v.adjacencies.get(j).vertexId]) return false;
            }
        }
        return true;
    }
}
