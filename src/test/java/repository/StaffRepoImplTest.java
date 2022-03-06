package repository;

import entities.Staff;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.HibernateSingleton;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StaffRepoImplTest {

    private StaffRepoImpl staffRepo=new StaffRepoImpl();


    @BeforeAll
    static void setup(){
        var sessionFactory= HibernateSingleton.getInstance();
    }



//add and findById
    @Test
    void add() {

        //arrange
        var staff=new Staff(null,"firstName","firstName","Username","password");

        //act
        staffRepo.add(staff);

        //assert
        Staff loadedStaff= (Staff) staffRepo.showInfo(staff.getId());
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
        var staff=new Staff(null,"firstName","firstName","Username","password");


        //act
        staffRepo.add(staff);


        //assert
        staffRepo.remove(staff);
        List<Staff> accountList=staffRepo.show();
        assertEquals(0,accountList.size());
    }

    @Test
    void update() {
    }

    @Test
    void showInfo() {
    }


    @AfterEach
    void tearDown() {
    }

}