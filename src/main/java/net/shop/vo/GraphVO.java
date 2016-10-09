package net.shop.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Langley on 30/09/2016.
 */
public class GraphVO implements Serializable{
    private static final long serialVersionUID = 3770780957368719862L;

    private List<NodeVO> nodes;
    private List<EdgeVO> edges;

    public List<NodeVO> getNodes() {
        return nodes;
    }

    public void setNodes(List<NodeVO> nodes) {
        this.nodes = nodes;
    }

    public List<EdgeVO> getEdges() {
        return edges;
    }

    public void setEdges(List<EdgeVO> edges) {
        this.edges = edges;
    }
}

