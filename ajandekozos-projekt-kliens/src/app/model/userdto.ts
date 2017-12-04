export class UserDTO {
    public id: number

    constructor(
        private firstname: string,
        private lastname: string,
    ) 
    {}
  
    public getFirstName(): string {
        return this.firstname;
    }

    public getLastName(): string {
        return this.lastname;
    }    

  }