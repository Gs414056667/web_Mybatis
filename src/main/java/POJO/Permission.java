package POJO;


public class Permission {

  private int id;
  private String permission_name;
  private String permission_status;
  private String qxsm;


  public long getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getQxsm() {
    return qxsm;
  }

  public void setQxsm(String qxsm) {
    this.qxsm = qxsm;
  }

  public String getPermission_name() {
    return permission_name;
  }

  public void setPermission_name(String permission_name) {
    this.permission_name = permission_name;
  }

  public String getPermission_status() {
    return permission_status;
  }

  public void setPermission_status(String permission_status) {
    this.permission_status = permission_status;
  }
}
