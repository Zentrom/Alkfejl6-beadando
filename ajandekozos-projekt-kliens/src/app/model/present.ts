import { User } from './user';

export class Present {
    constructor(
        public id: number,
        public name: string,
        public price: number,
        public link: string,
        public hidden: boolean,
        public user: User = null,
    ) {}
}