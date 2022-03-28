package services;

import entities.LessonTerm;
import entities.Staff;
import entities.Student;
import repository.StaffRepo;
import exceptionHandler.DuplicateUser;
import exceptionHandler.NoSuchId;

import java.util.List;

public class StaffRepoServices implements BaseServices<Staff> {

    private static StaffRepo staffRepo=new StaffRepo();

    @Override
    public Staff add(Staff staff) {
        if (checkUsername(staff.getUsername())){
            throw new DuplicateUser("username already exists!");
        }
        return staffRepo.add(staff);
    }

    @Override
    public Staff remove(int id) {

        if (!checkId(id)) {
            throw new NoSuchId();
        }
        Staff returnedStaff=staffRepo.showInfo(Staff.class,id);
        return staffRepo.remove(returnedStaff);
    }

    @Override
    public void update(Staff staff) {

        if (!checkId(staff.getId())){
            throw new NoSuchId();
        }
        staffRepo.update(staff);
    }

    @Override
    public Staff showInfo(int id) {

        if (!checkId(id)){
            throw new NoSuchId();
        }
        return staffRepo.showInfo(Staff.class,id);
    }

    @Override
    public List<Staff> showAll() {
        return staffRepo.showAll(Staff.class);
    }



    public Staff showInfo(String username) {

        if (!checkUsername(username)){
            throw new NoSuchId();
        }
        return staffRepo.showInfo(Staff.class,username);
    }


    public boolean checkUsername(String username){

        boolean flag=false;
        List<Staff> staffList=showAll();

        for (Staff a:staffList){
            if (username.equals(a.getUsername())){
                flag=true;
                break;
            }
        }
        return flag;
    }

//checking if the id exists in order to handle null pointer exception
//of nat having an entity with such id
    public boolean checkId(int Id){

        boolean flag=false;
        List<Staff> staffList=showAll();

        for (Staff s:staffList){
            if (s.getId()==Id){
                flag=true;
                break;
            }
        }
        return flag;
    }


    public void addAdmin(){

        Staff admin=new Staff(null,null,null,"admin","admin",0);
        if (checkUsername("admin"));

        else add(admin);
    }


    //staff log in
    public boolean logIn(String username, String password) {

        boolean logInCheck = false;
        Staff staff = showInfo(username);

        if (staff.getPassword().equals(password)) {
            logInCheck = true;
        }
        return logInCheck;
    }



}
