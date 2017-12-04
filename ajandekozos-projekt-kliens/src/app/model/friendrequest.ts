import { User } from './user';

export enum Status {
    PENDING, ACCEPTED, DECLINED
}

export class FriendRequest {
    public id: number;

    constructor(
        private requesterId: number,
        private requesteeId: number,
        private requesterName: string,
        private requesteeName: string,
        private status: Status
    ) {}

    public getRequester(): number {
        return this.requesterId;
    }

    public getRequestee(): number {
        return this.requesteeId;
    }

    public getRequesterName(): string {
        return this.requesterName;
    }
    
    public getRequesteeName(): string {
        return this.requesteeName;
    }

    public getStatus(): Status {
        return this.status;
    }

}