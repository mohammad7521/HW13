package services;

import entities.Staff;
import repository.StaffRepo;
import utils.DuplicateUser;

import java.util.List;

public class StaffRepoServices implements BaseServices<Staff> {

    private static StaffRepo staffRepo=new StaffRepo();

    @Override
    public Staff add(Staff staff) {

        if (staffRepo.showInfo(staff.getUsername(),Staff.class)!=null) {
            return staffRepo.add(staff);
        }
        else throw new DuplicateUser("username already exists");
    }

    @Override
    public Staff remove(int id) {

        Staff returnedStaff=staffRepo.showInfo(id,Staff.class);
        return staffRepo.remove(returnedStaff);
    }

    @Override
    public void update(Staff staff) {
        staffRepo.update(staff);
    }

    @Override
    public Staff showInfo(int id) {
        return staffRepo.showInfo(id,Staff.class);
    }

    @Override
    public List<Staff> showAll() {
        return staffRepo.showAll(Staff.class);
    }


    public Staff showInfo(String username) {
        return null;
    }
}
