package service;

import dao.CategoryDAO;
import dao.RecordDAO;
import entity.Category;
import entity.Record;

import java.util.Collections;
import java.util.List;

public class CategoryService {
    CategoryDAO categoryDAO=new CategoryDAO();
    RecordDAO recordDAO=new RecordDAO();

    public List<Category> list(){
        List<Category> cs=categoryDAO.list();
        for (Category c:cs){
            List<Record> rs=recordDAO.list(c.id);
            c.recordNumber=rs.size();
        }
        Collections.sort(cs,(c1,c2)->c2.recordNumber-c1.recordNumber);

        return cs;
    }

    public void add(String name){
        Category category=new Category();
        category.name=name;
        categoryDAO.add(category);
    }

    public void update(int id,String name){
        Category category=new Category();
        category.id=id;
        category.name=name;
        categoryDAO.update(category);
    }

    public void delete(int id){
        categoryDAO.delete(id);
    }

    public String getName(int id){
        return categoryDAO.get(id).name;
    }
}
