import { useEffect } from 'react';
import { useSetRecoilState } from 'recoil';
import {userState} from "@/store/user";


export default function AuthListener() {
    const setUser = useSetRecoilState(userState);

    useEffect(() => {
        const handler = (e: Event) => {
            const custom = e as CustomEvent;
            if (custom.detail?.user) {
                setUser(custom.detail.user);1
            }
        };

        window.addEventListener('auth:refreshed', handler);
        return () => window.removeEventListener('auth:refreshed', handler);
    }, [setUser]);

    return null;
}
