package services;

import entities.Staff;
import repository.StaffRepo;

public class StaffRepoServices implements BaseServices<Staff> {

    private static StaffRepo staffRepo=new StaffRepo();

    @Override
    public Staff add(Staff staff) {
        return staffRepo.add(staff);
    }

    @Override
    public Staff remove(long id) {

        Staff returnedStaff=staffRepo.showInfo(id,Staff.class);
        return staffRepo.remove(returnedStaff);
    }

    @Override
    public void update(Staff staff) {
        staffRepo.update(staff);
    }

    @Override
    public Staff showInfo(long id) {
        return staffRepo.showInfo(id,Staff.class);
    }
}
