import { UserDTO } from './userdto';

export class FriendRequest {
    constructor(
        public id: number,
        public requester: UserDTO,
        public requestee: UserDTO,
    ) {}
}