package graph;

public interface IGraph{
    void add(Integer src , Integer des);
    void remove(Integer src , Integer des);
    Integer query(Integer src , Integer des);
}