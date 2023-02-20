import java.util.ArrayList;

public class Vertex implements Comparable<Vertex>{
    int vertexId;
    int degree;
    ArrayList<Vertex> adjacencies;

    public Vertex(int vertexId) {
        this.vertexId = vertexId;
        degree = 0;
        adjacencies = new ArrayList<Vertex>();
    }

    public void addAdjacency(Vertex vertex){
        adjacencies.add(vertex);
        degree++;
    }
    @Override
    public int compareTo(Vertex o) {
        if(this.degree == o.degree){
            return 0;
        }
        if(this.degree>o.degree){
            return 1;
        }
        return -1;
    }
}
