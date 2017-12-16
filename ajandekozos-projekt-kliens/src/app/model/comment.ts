import { UserDTO } from './userdto';

export class Comment {
    constructor(
        public id: number,
        public text: string,
        public timeStamp: Date,
        public author: UserDTO
    ) {}
}