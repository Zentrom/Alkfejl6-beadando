export enum Role {
    GUEST, USER, ADMIN
  }
  
  export class User {
    username: String;
    password: String;
    firstname: String;
    lastname: String;
    email: String;
    role: Role;
  
    constructor(username?: String, password?: String, firstname?: String, lastname?:String, email?: String, role?: Role) {
      this.username = username || "";
      this.password = password || "";
      this.firstname = firstname || "";
      this.lastname = lastname || "";
      this.email = email || "";
      this.role = role || Role.GUEST;
      
    }

    /*adminUser: User;

    ngOnInit(){
      this.adminUser= new User("admin","admin","admin@gmail.com",Role.ADMIN);
    }

    public Username(){
      return this.adminUser.username;
    }*/
  }