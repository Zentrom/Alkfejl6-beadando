import { User } from './user';

export enum Status {
    PENDING, ACCEPTED, DECLINED
}

export class FriendRequest {
    public id: number;

    constructor(
        private requester: User,
        private requestee: User,
        private status: Status
    ) {}

    public getId(): number {
        return this.id;
    }

    public getRequester(): User {
        return this.requester;
    }

    public getRequestee(): User {
        return this.requestee;
    }

    public getStatus(): Status {
        return this.status;
    }

}