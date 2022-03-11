package repository;

import entities.Staff;
import org.junit.jupiter.api.*;
import utils.HibernateSingleton;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StaffRepoTest {

    private StaffRepo staffRepo=new StaffRepo();


    @BeforeAll
    static void setup(){
        var sessionFactory= HibernateSingleton.getInstance();
    }



//add and showInfo
    @Test
    void addAndShowInfo() {

        //arrange
        var staff=new Staff(null,"firstName","firstName","Username","password",50000000);

        //act
        staffRepo.add(staff);

        //assert
        Staff loadedStaff=staffRepo.showInfo(staff.getId(),Staff.class);
        assertAll(
                ()->assertNotNull(loadedStaff),
                ()->assertEquals(loadedStaff.getId(),staff.getId()),
                ()->assertEquals(loadedStaff.getFirstName(),staff.getFirstName()),
                ()->assertEquals(loadedStaff.getFirstName(),staff.getLastName()),
                ()->assertEquals(loadedStaff.getUsername(),staff.getUsername()),
                ()->assertEquals(loadedStaff.getPassword(),staff.getPassword())
        );
    }

    @Test
    void remove() {

        //arrange
        var staff=new Staff(null,"firstName","firstName","Username","password",50000000);


        //act
        staffRepo.add(staff);


        //assert
        staffRepo.remove(staff);
        List<Staff> accountList=staffRepo.showAll(Staff.class);
        assertEquals(0,accountList.size());
    }

    @Test
    void update() {
        //arrange
        var staff=new Staff(null,"firstName","firstName","Username","password",50000000);



        //act
        staffRepo.add(staff);


        //assert
        staff.setFirstName("reza");
        staff.setLastName("mohammadi");
        staffRepo.update(staff);

        Staff loadedStaff=staffRepo.showInfo(staff.getId(),Staff.class);
        assertAll(
                ()->assertNotNull(loadedStaff),
                ()->assertEquals(loadedStaff.getId(),staff.getId()),
                ()->assertEquals(loadedStaff.getUsername(),staff.getUsername()),
                ()->assertEquals(loadedStaff.getFirstName(),staff.getFirstName()),
                ()->assertEquals(loadedStaff.getLastName(),staff.getLastName()),
                ()->assertEquals(loadedStaff.getPassword(),staff.getPassword())
        );
    }


    @AfterEach
    void tearDown() {
        staffRepo.hqlTruncate("Staff");
    }

}