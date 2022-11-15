package com.revature.data.records;
import java.util.List;

public record StartOrderItems(
        List<Languages> languages,
        List<Tools> tools,
        List<Premades> premades
) {}
