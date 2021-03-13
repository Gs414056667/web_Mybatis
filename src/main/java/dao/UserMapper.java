package dao;

import POJO.Permission;
import POJO.Users;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    Users login(Map<String,Object> map);
    int register(Map<String,Object> map);
    int updatepsw(Map<String, Object> map);
    int updatestatus(Map<String, Object> map);
    List<Users> getAlluser();
    String getpermissioncode(String role);
    int updaterole(Map<String, Object> map);
    int updaterolepermissioncode(Map<String, Object> map);
    int addrole(String rolename);
    List<Permission> getAllpermission();
}
