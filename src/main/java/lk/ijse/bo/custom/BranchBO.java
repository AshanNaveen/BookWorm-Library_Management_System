package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.BranchDTO;

import java.util.List;

public interface BranchBO extends SuperBO {
    List<BranchDTO> loadAll();

    void save(BranchDTO branchDTO);

    void delete(BranchDTO dto);

    boolean update(BranchDTO branchDTO);
}
