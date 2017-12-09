import { UserDTO } from './userdto';

export class Present {
    public id: number;
    public user: UserDTO = null;
    
    constructor(
        public name: string,
        public price: number,
        public link: string,
        public hidden: boolean
    ) {}
}