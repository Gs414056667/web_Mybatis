package dao;

import POJO.Form;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface FormMapper {
    boolean check_added(Map<String,Object> map);
    boolean check_name(String username);
    boolean check_instcode(String instcode);
    boolean check_inst(String inst);
    int addform(Map<String,Object> map);
    List<Form> getformBybm(String bm);
    List<Form> getformByinst(String inst);
    Form getformBycode(String code);
    List<Form> getAllform();
    int role_permissioncode(Map<String, Object> map);
    List<Form> getform_tocheck();
    List<Form> Fuzzy_query(@Param("jgmc") String jgmc,@Param("szdy") String szdy,@Param("lxr") String lxr,@Param("gjz") String gjz,@Param("yjlx") String yjlx,@Param("ntrzj") String ntrzj);
    List<Map<String, Object>> Tj_search_jgsx(@Param("tjzd") String tjzd);
    List<Map<String, Object>> Tj_search_szdy(@Param("tjzd") String tjzd);
    List<Map<String, Object>> Tj_search_yjlx(@Param("tjzd") String tjzd);
    int xssh_agree(Map<String, Object> map);
    int xssh_disagree(Map<String, Object> map);
    int bmsh_agree(Map<String, Object> map);
    int bmsh_disagree(Map<String, Object> map);
    int add_permission(Map<String, Object> map);
    int delete_permission(Map<String, Object> map);
    int delete_form(Map<String, Object> map);
    int status_permission(Map<String, Object> map);
    List<Form> general_inquiry(@Param("sql") String sql);
    List<Map<String, Object>> TopN_ip(@Param("N") String N);
    List<Map<String, Object>> TopN_traffic(@Param("N") String N);
    List<Map<String, Object>> TopN_type(@Param("N") String N);
}
